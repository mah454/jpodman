package ir.moke.jpodman;

import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class PodmanSSE implements Closeable {
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

    public void containerStats(String name, Integer count, Consumer<String> stateConsumer) {
        URI targetURI = baseURI.resolve("containers/%s/stats".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            Stream<String> stream = client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
            if (count != null) {
                stream.limit(count).forEach(stateConsumer);
            } else {
                stream.forEach(stateConsumer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void containerTop(String name, Integer count, Consumer<String> topConsumer) {
        URI targetURI = baseURI.resolve("containers/%s/top".formatted(name));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            Stream<String> stream = client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
            if (count != null) {
                stream.limit(count).forEach(topConsumer);
            } else {
                stream.forEach(topConsumer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void systemEvents(Integer count, Consumer<String> eventConsumer) {
        URI targetURI = baseURI.resolve("events");
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            Stream<String> stream = client.send(request, HttpResponse.BodyHandlers.ofLines()).body();
            if (count != null) {
                stream.limit(count).forEach(eventConsumer);
            } else {
                stream.forEach(eventConsumer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void containerLogs(String name, boolean stdOut, boolean stdErr, boolean timestamp, int tail, boolean follow, Consumer<String> logConsumer) {
        String url = "containers/%s/logs?stdout=%s&stderr=%s&timestamps=%s&tail=%s&follow=%s";
        URI targetURI = baseURI.resolve(url.formatted(name, stdOut, stdErr, timestamp, tail, follow));
        try {
            var request = HttpRequest.newBuilder(targetURI).GET().build();
            client.send(request, HttpResponse.BodyHandlers.ofLines())
                    .body()
                    .map(item -> timestamp ? item.substring(8) : item)
                    .filter(item -> timestamp || !item.startsWith("\u0001"))
                    .forEach(logConsumer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (executorService != null) {
            executorService.shutdown();
        }
        client.close();
    }
}
