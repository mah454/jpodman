package ir.moke.jpodman;

import ir.moke.jpodman.pojo.ExecStartInstance;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public void exec(String execId, ExecStartInstance execStartInstance, InputStream stdIn, OutputStream stdOut) {
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

            es.execute(() -> processOutputStream(stdOut, socket));
            processInputStream(stdIn, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void attach(String containerId, InputStream stdIn, OutputStream stdOut) {
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

            es.execute(() -> processOutputStream(stdOut, socket));
            processInputStream(stdIn, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void processInputStream(InputStream stdIn, PrintWriter writer) {
        if (stdIn != null) {
            // Read input from the user and send it to the container
            try {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = stdIn.read(buffer)) != -1) {
                    writer.print(new String(buffer, 0, bytesRead));
                    writer.flush();
                }
            } catch (Exception e) {
                throw new RuntimeException("Podman input stream socket io error");
            }
        }
    }

    private static void processOutputStream(OutputStream stdOut, Socket socket) {
        if (stdOut != null) {
            // Start a thread to read output from the container
            try {
                int data;
                while ((data = socket.getInputStream().read()) != -1) {
                    stdOut.write(data);
                    stdOut.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException("Podman output stream socket io error");
            }
        }
    }
}
