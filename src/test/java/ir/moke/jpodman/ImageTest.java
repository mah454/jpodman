package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;
import ir.moke.jpodman.service.PodmanImageService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ImageTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman();
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);

    @Test
    public void imagePull() {
        try (Response response = podmanImageService.imagePull(IMAGE_REGISTRY + "busybox")) {
            Assertions.assertEquals(response.getStatus(), 200);
            System.out.println(response.readEntity(String.class));
        }
    }

    @Test
    public void imageList() {
        List<Image> imageList = podmanImageService.imageList(true);
        System.out.println(imageList.size());
    }

    @Test
    public void imageExists() {
        try (Response response = podmanImageService.imageExists("busybox")) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    public void imageRemove() {
        try (Response response = podmanImageService.imageRemove(List.of("busybox"), false, false)) {
            Assertions.assertEquals(response.getStatus(), 200);
        }
    }

    @Test
    public void imageSearch() {
        List<SearchImage> searchImageList = podmanImageService.imageSearch("mysql");
        Assertions.assertTrue(searchImageList.size() > 1);
    }
}
