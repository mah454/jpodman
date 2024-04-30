package ir.moke.jpodman;

import ir.moke.jpodman.utils.JsonUtils;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.media.sse.SseFeature;

import java.io.Closeable;

public class Podman implements Closeable {
    private static final String baseURL = "http://%s:%s/v5/libpod/";
    private static final Client client;

    private final String host;
    private final int port;

    public Podman(String host, int port) {
        this.host = host;
        this.port = port;
    }

    static {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(JsonUtils.getObjectMapper());

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        clientConfig.register(SseFeature.class);
        clientConfig.register(provider);

        client = ClientBuilder.newClient(clientConfig);
    }

    public <T> T api(Class<T> clazz) {
        return WebResourceFactory.newResource(clazz, client.target(baseURL.formatted(host, port)));
    }

    @Override
    public void close() {
        client.close();
    }
}
