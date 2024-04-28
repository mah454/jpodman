package ir.moke.jpodman;

import ir.moke.jpodman.service.PodmanVolumeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

public class VolumeTest {

    private static final String IMAGE_REGISTRY = "registry.docker.ir/";
    private static final Podman podman = new Podman();
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    public void volumeCreate() {
        try (Response response = podmanVolumeService.volumeCreate("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
        }
    }
}
