package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.jpodman.service.PodmanVolumeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VolumeTest {

    private static final Podman podman = new Podman();
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    @Order(0)
    public void volumeCreate() {
        Volume volume = new Volume();
        volume.setName("sample");
        volume = podmanVolumeService.volumeCreate(volume);
        Assertions.assertNotNull(volume.getCreatedAt());
    }

    @Test
    @Order(1)
    public void volumeList() {
        List<Volume> volumes = podmanVolumeService.volumeList();
        Assertions.assertFalse(volumes.isEmpty());
    }

    @Test
    @Order(2)
    public void volumeRemove() {
        try (Response response = podmanVolumeService.volumeRemove("sample")) {
            Assertions.assertEquals(204, response.getStatus());
        }
    }

    @Test
    @Order(3)
    public void volumePrune() {
        volumeCreate();
        try (Response response = podmanVolumeService.volumePrune()) {
            Assertions.assertEquals(200, response.getStatus());
        }
    }
}
