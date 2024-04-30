package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;
import ir.moke.jpodman.service.PodmanImageService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);

    @Test
    @Order(0)
    public void imagePull() {
        try (Response response = podmanImageService.imagePull(IMAGE_REGISTRY + "busybox")) {
            Assertions.assertEquals(response.getStatus(), 200);
            System.out.println(response.readEntity(String.class));
        }
    }

    @Test
    @Order(1)
    public void imageList() {
        List<Image> imageList = podmanImageService.imageList(true);
        System.out.println(imageList.size());
    }

    @Test
    @Order(2)
    public void imageExists() {
        try (Response response = podmanImageService.imageExists("busybox")) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    @Order(3)
    public void imageRemove() {
        try (Response response = podmanImageService.imageRemove(List.of("busybox"), false, false)) {
            Assertions.assertEquals(response.getStatus(), 200);
        }
    }

    @Test
    @Order(4)
    public void imageSearch() {
        List<SearchImage> searchImageList = podmanImageService.imageSearch("mysql");
        Assertions.assertTrue(searchImageList.size() > 1);
    }

    @Test
    @Order(5)
    public void imagePrune() {
        try (Response response = podmanImageService.imagePrune()) {
            Assertions.assertEquals(response.getStatus(), 200);
        }
    }
}
