package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.jpodman.service.PodmanVolumeService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VolumeTest {

    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    @Order(0)
    public void volumeCreate() {
        Volume volume = new Volume();
        volume.setName("sample");
        volume = podmanVolumeService.volumeCreate(volume);
        Assertions.assertNotNull(volume.getCreatedAt());
        System.out.println(volume.getMountPoint());
    }

    @Test
    @Order(1)
    public void volumeList() {
        List<Volume> volumes = podmanVolumeService.volumeList();
        Assertions.assertFalse(volumes.isEmpty());
        for (Volume volume : volumes) {
            System.out.println(volume.getMountPoint());
        }
    }

    @Test
    @Order(2)
    public void volumeRemove() {
        HttpResponse<Void> httpResponse = podmanVolumeService.volumeRemove("sample", false);
        Assertions.assertEquals(204, httpResponse.statusCode());
    }

    @Test
    @Order(3)
    public void volumePrune() {
        HttpResponse<Void> httpResponse = podmanVolumeService.volumePrune();
        Assertions.assertEquals(200, httpResponse.statusCode());
    }
}
