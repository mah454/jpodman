package ir.moke.jpodman;

import ir.moke.jpodman.utils.JsonUtils;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import java.io.Closeable;
import java.io.IOException;

public class Podman implements Closeable {
    private static final String baseURL = "http://127.0.0.1:9000/v5.0.0.0/libpod/";

    private static final Client client;

    static {

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(JsonUtils.getObjectMapper());

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        clientConfig.register(provider);

        client = ClientBuilder.newClient(clientConfig);
    }

    public <T> T api(Class<T> clazz) {
        return WebResourceFactory.newResource(clazz, client.target(baseURL));
    }

    @Override
    public void close() throws IOException {
        client.close();
    }
}
