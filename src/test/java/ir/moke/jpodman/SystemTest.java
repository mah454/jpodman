package ir.moke.jpodman;

import ir.moke.jpodman.service.PodmanSystemService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SystemTest {

    private static final Podman podman = new Podman("127.0.0.1",9000);
    private static final PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1",9000);
    private static final PodmanSystemService podmanSystemService = podman.api(PodmanSystemService.class);

    @Test
    @Order(0)
    public void ping() {
        try (Response response = podmanSystemService.ping()) {
            System.out.println(response.getStatus());
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(1)
    public void info() {
        try (Response response = podmanSystemService.info()) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(2)
    public void df() {
        try (Response response = podmanSystemService.df()) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(2)
    public void event() {
        Stream<String> stream = podmanSSE.systemEvents();
        stream.forEach(System.out::println);
    }
}
