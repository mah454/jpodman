package ir.moke.jpodman;

import ir.moke.jpodman.pojo.*;
import ir.moke.jpodman.service.PodmanContainerService;
import ir.moke.jpodman.service.PodmanImageService;
import ir.moke.jpodman.service.PodmanVolumeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContainerTest {
    private static final String IMAGE_NAME = "registry.docker.ir/postgres";
    private static final Podman podman = new Podman();
    private static final PodmanContainerService podmanContainerService = podman.api(PodmanContainerService.class);
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);

    @Test
    @Order(0)
    public void containerCreate() {
        if (checkContainerExists()) {
            containerDelete();
        }
        List<Volume> currentVolumes = podmanVolumeService.volumeList();

        currentVolumes.stream()
                .filter(item -> item.getName().equals("sample-volume"))
                .findFirst()
                .map(Volume::getName)
                .ifPresent(ContainerTest::removeTestVolume);

        try (Response response = podmanImageService.imageExists(IMAGE_NAME)) {
            if (response.getStatus() != 204) {
                try (Response pullResponse = podmanImageService.imagePull(IMAGE_NAME)) {
                    Assertions.assertEquals(pullResponse.getStatus(), 200);
                    System.out.println(pullResponse.readEntity(String.class));
                }
            }
        }

        Container container = new Container();
        container.setName("test");
        container.setImage(IMAGE_NAME);
        container.setHostname("test-api");
        container.setEnvironments(Map.of("POSTGRES_PASSWORD", "adminpass"));
        container.setDnsSearch(List.of("google.com", "yahoo.com"));
        container.setDnsServer(List.of("8.8.8.8", "4.2.2.2", "217.218.127.127"));
        container.setVolumes(List.of(new VolumeParameter("/target", "sample-volume")));
        container.setExpose(Map.of(8080, Protocol.TCP, 9090, Protocol.UDP));
        container.setHosts(List.of("linux:10.10.11.11"));
        container.setPortMappings(List.of(new PortMapping(8080, 8081), new PortMapping(2121, 9091, Protocol.UDP)));

//        container.setStaticIp("172.16.10.10"); // need run as root
//        container.setUseHostEnvironments(true);
//        container.setEntrypoint(List.of("/bin/sh"));

        Response response = podmanContainerService.containerCreate(container);
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
        response.close();

        Assertions.assertEquals(201, response.getStatus());
    }

    @Test
    @Order(1)
    public void containerExists() {
        boolean exists = checkContainerExists();
        Assertions.assertTrue(exists);
    }

    @Test
    @Order(2)
    public void containerList() {
        List<ContainerInfo> containerInfoList = podmanContainerService.containerList(true, null);
        Assertions.assertFalse(containerInfoList.isEmpty());
    }

    @Test
    @Order(3)
    public void containerStart() {
        try (Response response = podmanContainerService.containerStart("test")) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    @Order(4)
    public void containerState() {
        Stream<String> stream = PodmanSSE.containerStats("test");
        stream.limit(3).forEach(Assertions::assertNotNull);
    }

    @Test
    @Order(5)
    public void containerTop() {
        Stream<String> stream = PodmanSSE.containerTop("test");
        stream.limit(3).forEach(Assertions::assertNotNull);
    }

    @Test
    @Order(6)
    public void containerStop() {
        try (Response response = podmanContainerService.containerStop("test", 3)) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    @Order(7)
    public void containerDelete() {
        try (Response response = podmanContainerService.containerDelete("test", true, true)) {
            Assertions.assertEquals(response.getStatus(), 200);
        }
    }

    private static void removeTestVolume(String name) {
        Response response = podmanVolumeService.volumeRemove(name);
        response.close();
    }

    private static boolean checkContainerExists() {
        try (Response response = podmanContainerService.containerExists("test")) {
            int status = response.getStatus();
            return status == 204;
        }
    }
}
