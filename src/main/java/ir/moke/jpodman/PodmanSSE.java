package ir.moke.jpodman;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

public class PodmanSSE {
    private static final String baseURL = "http://%s:%s/v5/libpod/";
    private final HttpClient client;
    private final URI baseURI;
    private final ExecutorService executorService;

    public PodmanSSE(String host, int port) {
        try {
            this.executorService = null;
            baseURI = new URI(baseURL.formatted(host, port));
            this.client = HttpClient.newHttpClient();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public PodmanSSE(String host, int port, ExecutorService executorService) {
        try {
            this.executorService = executorService;
            baseURI = new URI(baseURL.formatted(host, port));
            HttpClient.Builder builder = HttpClient.newBuilder();
            Optional.ofNullable(executorService).ifPresent(builder::executor);
            client = builder.build();
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

    public Stream<String> containerLogs(String name, boolean stdOut, boolean stdErr, boolean timestamp, int tail, boolean follow) {
        String url = "containers/%s/logs?stdout=%s&stderr=%s&timestamps=%s&tail=%s&follow=%s";
        URI targetURI = baseURI.resolve(url.formatted(name, stdOut, stdErr, timestamp, tail, follow));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        client.close();
    }
}
