package ir.moke.jpodman;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class PodmanSSE implements Closeable {
    private static final String baseURL = "http://127.0.0.1:9000/v5.0.0.0/libpod/";
    private static final URI baseURI;
    private static final HttpClient client = HttpClient.newHttpClient();

    static {
        try {
            baseURI = new URI(baseURL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> containerStats(String name) {
        URI targetURI = baseURI.resolve("containers/%s/stats".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> containerTop(String name) {
        URI targetURI = baseURI.resolve("containers/%s/top".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> systemEvents() {
        URI targetURI = baseURI.resolve("events");
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
