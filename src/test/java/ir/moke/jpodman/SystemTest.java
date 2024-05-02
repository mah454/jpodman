package ir.moke.jpodman;

import ir.moke.jpodman.service.PodmanSystemService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SystemTest {

    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1", 9000);
    private static final PodmanSystemService podmanSystemService = podman.api(PodmanSystemService.class);

    @Test
    @Order(0)
    public void ping() {
        HttpResponse<String> httpResponse = podmanSystemService.ping();
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());

    }

    @Test
    @Order(1)
    public void info() {
        String info = podmanSystemService.info();
        Assertions.assertNotNull(info);
    }

    @Test
    @Order(2)
    public void df() {
        String df = podmanSystemService.df();
        Assertions.assertNotNull(df);
    }

    @Test
    @Order(2)
    public void event() {
        Stream<String> stream = podmanSSE.systemEvents();
        stream.forEach(System.out::println);
    }
}
