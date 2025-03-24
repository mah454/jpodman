package ir.moke.jpodman;

import ir.moke.jpodman.pojo.ExecStartInstance;

import java.io.*;
import java.net.Socket;

public class PodmanIO {
    private static final String PODMAN_API_VERSION = "v5.0.0";
    private final String host;
    private final int port;

    public PodmanIO(String host, int port) {
        this.host = host;
        this.port = port;
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

            if (stdOut != null) {
                // Start a thread to read output from the container
                Thread outputThread = new Thread(() -> {
                    try {
                        int data;
                        while ((data = socket.getInputStream().read()) != -1) {
                            stdOut.write(data);
                            stdOut.flush();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Podman exec socket closed");
                    }
                });
                outputThread.start();
            }

            if (stdIn != null) {
                // Read input from the user and send it to the container
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = stdIn.read(buffer)) != -1) {
                    writer.print(new String(buffer, 0, bytesRead));
                    writer.flush();
                }
            }
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

            if (stdOut != null) {
                // Start a thread to read output from the container
                Thread outputThread = new Thread(() -> {
                    try {
                        int data;
                        while ((data = socket.getInputStream().read()) != -1) {
                            stdOut.write(data);
                            stdOut.flush();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                outputThread.start();
            }

            if (stdIn != null) {
                // Read input from the user and send it to the container
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = stdIn.read(buffer)) != -1) {
                    writer.print(new String(buffer, 0, bytesRead));
                    writer.flush();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
