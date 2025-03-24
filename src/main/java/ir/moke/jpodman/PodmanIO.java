package ir.moke.jpodman;

import ir.moke.jpodman.pojo.ExecStartInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PodmanIO {
    private static final String PODMAN_API_VERSION = "v5.0.0";
    private final ExecutorService es;
    private final String host;
    private final int port;

    public PodmanIO(String host, int port) {
        this.host = host;
        this.port = port;
        this.es = Executors.newVirtualThreadPerTaskExecutor();
    }

    public PodmanIO(String host, int port, ExecutorService es) {
        this.host = host;
        this.port = port;
        this.es = es;
    }

    public void exec(String execId, ExecStartInstance execStartInstance, Supplier<byte[]> stdInSupplier, Consumer<byte[]> stdOutConsumer) {
        try (Socket socket = new Socket(host, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String body = execStartInstance.toJson();
            int length = body.length();

            // Send the WebSocket upgrade request
            String requestHeader = """
                    POST /%s/libpod/exec/%s/start HTTP/1.1
                    Host: %s:%s
                    Content-Type: application/json
                    Content-Length: %s
                    
                    %s
                    """.formatted(PODMAN_API_VERSION, execId, host, port, length, body);
            writer.write(requestHeader);
            writer.flush();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break; // End of headers
                }
            }

            es.execute(() -> processOutputStream(socket, stdOutConsumer));
            processInputStream(socket, stdInSupplier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void attach(String containerId, Supplier<byte[]> stdInSupplier, Consumer<byte[]> stdOutConsumer) {
        // Create a tcp connection
        try (Socket socket = new Socket(host, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send the tcp upgrade request
            String requestHeader = """
                    POST /%s/libpod/containers/%s/attach?stream=true&stdout=true&stdin=true&stderr=true HTTP/1.1
                    Host: %s:%s
                    Upgrade: tcp
                    Connection: Upgrade
                    
                    """.formatted(PODMAN_API_VERSION, containerId, host, port);
            writer.write(requestHeader);
            writer.flush();

            // Read the WebSocket upgrade response
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break; // End of headers
                }
            }

            es.execute(() -> processOutputStream(socket, stdOutConsumer));
            processInputStream(socket, stdInSupplier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void processInputStream(Socket socket, Supplier<byte[]> stdInSupplier) {
        if (stdInSupplier != null) {
            // Read input from the user and send it to the container
            try {
                socket.getOutputStream().write(stdInSupplier.get());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void processOutputStream(Socket socket, Consumer<byte[]> stdOutConsumer) {
        if (stdOutConsumer != null) {
            // Start a thread to read output from the container
            try {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                    stdOutConsumer.accept(Arrays.copyOf(buffer, bytesRead));
                }
            } catch (IOException e) {
                throw new RuntimeException("Podman output stream socket io error");
            }
        }
    }
}
