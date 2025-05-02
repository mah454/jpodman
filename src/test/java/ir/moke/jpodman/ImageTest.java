package ir.moke.jpodman;

import ir.moke.jpodman.service.PodmanImageService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);

    @Test
    @Order(0)
    public void imagePull() {
        HttpResponse<String> response = podmanImageService.imagePull(IMAGE_REGISTRY + "ubuntu", true);
        assertEquals(200, response.statusCode());
        System.out.println(response.body());
    }

    @Test
    @Order(1)
    public void imageList() {
        HttpResponse<String> response = podmanImageService.imageList(true);
        assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.body());
    }

    @Test
    @Order(2)
    public void imageExists() {
        HttpResponse<Void> response = podmanImageService.imageExists("busybox");
        assertEquals(204, response.statusCode());
    }

    @Test
    @Order(3)
    public void imageInspect() {
        HttpResponse<String> inspect = podmanImageService.imageInspect("busybox");
        System.out.println(inspect.body());
    }

    @Test
    @Order(4)
    public void imageRemove() {
        HttpResponse<Void> response = podmanImageService.imageRemove(List.of("busybox"), false, false);
        assertEquals(200, response.statusCode());
    }

    @Test
    @Order(5)
    public void checkExistsAfterRemove() {
        HttpResponse<Void> response = podmanImageService.imageExists("busybox");
        assertEquals(204, response.statusCode());
    }

    @Test
    @Order(6)
    public void imageSearch() {
        HttpResponse<String> response = podmanImageService.imageSearch("mysql");
        assertEquals(200, response.statusCode());
    }

    @Test
    @Order(7)
    public void imagePrune() {
        HttpResponse<String> response = podmanImageService.imagePrune();
        assertEquals(200, response.statusCode());
    }
}
