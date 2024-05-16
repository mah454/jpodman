package ir.moke.jpodman;

import ir.moke.kafir.http.Kafir;

import javax.net.ssl.SSLContext;
import java.net.http.HttpClient;
import java.util.Optional;

public class Podman {
    private static final String baseURL = "http://%s:%s/v5/libpod/";

    private final String host;
    private final int port;
    private final SSLContext sslContext;

    public Podman(String host, int port) {
        this.host = host;
        this.port = port;
        this.sslContext = null;
    }

    public Podman(String host, int port, SSLContext sslContext) {
        this.host = host;
        this.port = port;
        this.sslContext = sslContext;
    }

    public <T> T api(Class<T> clazz) {
        Kafir.KafirBuilder kafirBuilder = new Kafir.KafirBuilder().setBaseUri(baseURL.formatted(host, port)).setVersion(HttpClient.Version.HTTP_2);
        Optional.ofNullable(sslContext).ifPresent(item -> kafirBuilder.setSslContext(sslContext));

        return kafirBuilder.build(clazz);
    }
}
