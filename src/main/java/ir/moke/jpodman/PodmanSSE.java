package ir.moke.jpodman;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class PodmanSSE implements Closeable {
    private static final String baseURL = "http://%s:%s/v5/libpod/";
    private static final HttpClient client = HttpClient.newHttpClient();
    private final URI baseURI;

    public PodmanSSE(String host, int port) {
        try {
            baseURI = new URI(baseURL.formatted(host, port));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> containerStats(String name) {
        URI targetURI = baseURI.resolve("containers/%s/stats".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> containerTop(String name) {
        URI targetURI = baseURI.resolve("containers/%s/top".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> systemEvents() {
        URI targetURI = baseURI.resolve("events");
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> containerLogs(String name) {
        URI targetURI = baseURI.resolve("containers/%s/logs".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void close() {
        client.close();
    }
}
