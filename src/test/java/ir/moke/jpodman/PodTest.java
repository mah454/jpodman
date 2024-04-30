package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Container;
import ir.moke.jpodman.pojo.Pod;
import ir.moke.jpodman.pojo.PodInfo;
import ir.moke.jpodman.service.PodmanContainerService;
import ir.moke.jpodman.service.PodmanPodService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PodTest {

    private static final Podman podman = new Podman("127.0.0.1",9000);
    private static final PodmanPodService podmanPodService = podman.api(PodmanPodService.class);
    private static final PodmanContainerService podmanContainerService = podman.api(PodmanContainerService.class);

    @Test
    @Order(0)
    public void podCreate() {
        Pod pod = new Pod();
        pod.setName("sample");
        pod.setHosts(List.of("linux:172.16.12.12"));
        pod.setDnsServer(List.of("217.218.127.127"));
        pod.setLabels(Map.of("a", "12", "b", "44"));
        pod.setStaticIp("172.16.112.21");

        try (Response response = podmanPodService.podCreate(pod)) {
            Assertions.assertEquals(response.getStatus(), 201);
            System.out.println(response.readEntity(String.class));
        }

        Container container = new Container();
        container.setName("c1");
        container.setImage("postgres");
        container.setPod("sample");
        try (Response containerResponse = podmanContainerService.containerCreate(container)) {
            System.out.println(containerResponse.getStatus());
            System.out.println(containerResponse.readEntity(String.class));
        }
    }


    @Test
    @Order(1)
    public void podExists() {
        try (Response response = podmanPodService.podExists("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(204, response.getStatus());
        }
    }

    @Test
    @Order(2)
    public void podStart() {
        try (Response response = podmanPodService.podStart("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(3)
    public void podStop() {
        try (Response response = podmanPodService.podStop("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(4)
    public void podPause() {
        try (Response response = podmanPodService.podPause("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(4)
    public void podUnpause() {
        try (Response response = podmanPodService.podUnpause("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(5)
    public void podList() {
        List<PodInfo> podInfos = podmanPodService.podList();
        Assertions.assertFalse(podInfos.isEmpty());
    }

    @Test
    @Order(6)
    public void podKill() {
        podStart();
        try (Response response = podmanPodService.podKill("sample")) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    @Order(20)
    public void podRemove() {
        try (Response response = podmanPodService.podRemove("sample", true)) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            Assertions.assertEquals(200, response.getStatus());
        }
    }
}
