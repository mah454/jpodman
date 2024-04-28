package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.jpodman.service.PodmanVolumeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VolumeTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman();
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    public void volumeCreate() {
        Volume volume = new Volume();
        volume.setName("sample");
        volume = podmanVolumeService.volumeCreate(volume);
        Assertions.assertNotNull(volume.getCreatedAt());
    }

    @Test
    public void volumeRemove() {
        try (Response response = podmanVolumeService.volumeRemove("sample")) {
            Assertions.assertEquals(204, response.getStatus());
        }
    }

    @Test
    public void volumeList() {
        List<Volume> volumes = podmanVolumeService.volumeList();
        Assertions.assertFalse(volumes.isEmpty());
    }


    @Test
    public void volumePrune() {
        try (Response response = podmanVolumeService.volumePrune()) {
            Assertions.assertEquals(200, response.getStatus());
        }
    }
}
