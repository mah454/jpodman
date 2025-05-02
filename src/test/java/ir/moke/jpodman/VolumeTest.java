package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.jpodman.service.PodmanVolumeService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VolumeTest {

    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    @Order(0)
    public void volumeCreate() {
        Volume volume = new Volume();
        volume.setName("sample");
        HttpResponse<String> response = podmanVolumeService.volumeCreate(volume);
        Assertions.assertNotNull(response.body());
        System.out.println(response.body());
    }

    @Test
    @Order(1)
    public void volumeList() {
        HttpResponse<String> response = podmanVolumeService.volumeList();
        Assertions.assertEquals(200, response.statusCode());
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
