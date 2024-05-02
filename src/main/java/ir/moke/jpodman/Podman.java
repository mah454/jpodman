package ir.moke.jpodman;

import ir.moke.jpodman.http.Kafir;

import java.net.http.HttpClient;

public class Podman {
    private static final String baseURL = "http://%s:%s/v5/libpod/";

    private final String host;
    private final int port;

    public Podman(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T api(Class<T> clazz) {
        return new Kafir.KafirBuilder()
                .setBaseUri(baseURL.formatted(host, port))
                .setVersion(HttpClient.Version.HTTP_2)
                .build(clazz);
    }
}
