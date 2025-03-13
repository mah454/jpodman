package ir.moke.jpodman;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.function.Consumer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SseTest {

    @Test
    public void checkContainerLogs() {
        try (PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1", 9000)) {
            podmanSSE.containerLogs("main", true, true, true, 10, true, System.out::println);
        }
    }
}
