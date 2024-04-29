package ir.moke.jpodman;

import ir.moke.jpodman.pojo.*;
import ir.moke.jpodman.service.PodmanContainerService;
import ir.moke.jpodman.service.PodmanVolumeService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ContainerTest {

    private static final Podman podman = new Podman();
    private static final PodmanContainerService podmanContainerService = podman.api(PodmanContainerService.class);
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);

    @Test
    public void containerDelete() {
        try (Response response = podmanContainerService.containerDelete("test", true, true)) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    public boolean containerExists() {
        try (Response response = podmanContainerService.containerExists("test")) {
            int status = response.getStatus();
            return status == 204;
        }
    }

    @Test
    public void containerCreate() {
        if (containerExists()) {
            containerDelete();
        }
        List<Volume> currentVolumes = podmanVolumeService.volumeList();

        currentVolumes.stream()
                .filter(item -> item.getName().equals("sample-volume"))
                .findFirst()
                .map(Volume::getName)
                .ifPresent(ContainerTest::removeTestVolume);

        Container container = new Container();
        container.setName("test");
        container.setImage("registry.docker.ir/postgres");
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
    public void containerList() {
        List<ContainerInfo> containerInfosList = podmanContainerService.containerList(true, false);
        Assertions.assertFalse(containerInfosList.isEmpty());
    }

    @Test
    public void containerStart() {
        containerCreate();
        try (Response response = podmanContainerService.containerStart("test")) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    public void containerStop() {
        containerStart();
        try (Response response = podmanContainerService.containerStop("test", 3)) {
            Assertions.assertEquals(response.getStatus(), 204);
        }
    }

    @Test
    public void containerState() {
        containerStart();
        Stream<String> stream = PodmanSSE.containerStats("test");
        stream.forEach(System.out::println);
    }

    @Test
    public void containerTop() {
        containerStart();
        Stream<String> stream = PodmanSSE.containerTop("test");
        stream.forEach(System.out::println);
    }

    private static void removeTestVolume(String name) {
        Response response = podmanVolumeService.volumeRemove(name);
        response.close();
    }
}
