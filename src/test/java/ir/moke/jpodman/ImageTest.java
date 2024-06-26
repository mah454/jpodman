package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;
import ir.moke.jpodman.service.PodmanImageService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);

    @Test
    @Order(0)
    public void imagePull() {
        HttpResponse<String> response = podmanImageService.imagePull(IMAGE_REGISTRY + "busybox");
        Assertions.assertEquals(response.statusCode(), 200);
        System.out.println(response.body());
    }

    @Test
    @Order(1)
    public void imageList() {
        List<Image> images = podmanImageService.imageList(true);
        Assertions.assertNotNull(images);
        Assertions.assertFalse(images.isEmpty());
        Assertions.assertDoesNotThrow(() -> images.getFirst().getNames());
    }

    @Test
    @Order(2)
    public void imageExists() {
        HttpResponse<Void> response = podmanImageService.imageExists("busybox");
        Assertions.assertEquals(response.statusCode(), 204);
    }

    @Test
    @Order(3)
    public void imageInspect() {
        HttpResponse<Image> inspect = podmanImageService.imageInspect("busybox");
        System.out.println(inspect.body().getId());
    }

    @Test
    @Order(4)
    public void imageRemove() {
        HttpResponse<Void> response = podmanImageService.imageRemove(List.of("busybox"), false, false);
        Assertions.assertEquals(response.statusCode(), 200);
    }

    @Test
    @Order(5)
    public void checkExistsAfterRemove() {
        HttpResponse<Void> response = podmanImageService.imageExists("busybox");
        Assertions.assertEquals(response.statusCode(), 204);
    }

    @Test
    @Order(6)
    public void imageSearch() {
        List<SearchImage> searchImageList = podmanImageService.imageSearch("mysql");
        Assertions.assertTrue(searchImageList.size() > 1);
    }

    @Test
    @Order(7)
    public void imagePrune() {
        HttpResponse<String> response = podmanImageService.imagePrune();
        Assertions.assertEquals(response.statusCode(), 200);
    }

    private static void checkAsync(List<Image> images) {
        for (Image image : images) {
            System.out.println(image);
        }
    }
}
