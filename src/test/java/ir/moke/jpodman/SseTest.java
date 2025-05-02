package ir.moke.jpodman;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SseTest {

    @Test
    @Order(0)
    public void checkContainerLogs() {
        try (PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1", 9000)) {
            podmanSSE.containerLogs("main", true, true, true, 10, true, System.out::println);
        }
    }

    @Test
    @Order(1)
    public void imagePull() {
        try (PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1", 9000)) {
            podmanSSE.pullImage("registry.docker.ir/nginx", System.out::println);
        }
    }
}
