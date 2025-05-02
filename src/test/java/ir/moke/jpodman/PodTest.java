package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Container;
import ir.moke.jpodman.pojo.Pod;
import ir.moke.jpodman.pojo.PodStats;
import ir.moke.jpodman.service.PodmanContainerService;
import ir.moke.jpodman.service.PodmanPodService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PodTest {

    private static final Podman podman = new Podman("127.0.0.1", 9000);
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

        HttpResponse<String> podCreateResponse = podmanPodService.podCreate(pod);
        Assertions.assertEquals(podCreateResponse.statusCode(), 201);
        System.out.println(podCreateResponse.body());

        Container container = new Container();
        container.setName("c1");
        container.setImage("postgres");
        container.setPod("sample");
        HttpResponse<String> containerCreateResponse = podmanContainerService.containerCreate(container);
        Assertions.assertEquals(201, containerCreateResponse.statusCode());
        System.out.println(containerCreateResponse.body());
    }


    @Test
    @Order(1)
    public void podExists() {
        HttpResponse<Void> httpResponse = podmanPodService.podExists("sample");
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(204, httpResponse.statusCode());
    }

    @Test
    @Order(2)
    public void podStart() {
        HttpResponse<Void> httpResponse = podmanPodService.podStart("sample");
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(3)
    public void podStop() {
        HttpResponse<Void> httpResponse = podmanPodService.podStop("sample");
        System.out.println(httpResponse.body());
        System.out.println(httpResponse.statusCode());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(4)
    public void podPause() {
        HttpResponse<Void> httpResponse = podmanPodService.podPause("sample");
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(5)
    public void podUnpause() {
        HttpResponse<Void> httpResponse = podmanPodService.podUnpause("sample");
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(6)
    public void podList() {
        HttpResponse<String> podInfos = podmanPodService.podList();
        Assertions.assertNotNull(podInfos);
    }

    @Test
    @Order(7)
    public void podKill() {
        podStart();
        HttpResponse<Void> httpResponse = podmanPodService.podKill("sample");
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(7)
    public void podStats() {
        List<PodStats> podStats = podmanPodService.podStats(true, Collections.emptyList());
        Assertions.assertFalse(podStats.isEmpty());
    }

    @Test
    @Order(20)
    public void podRemove() {
        HttpResponse<Void> httpResponse = podmanPodService.podRemove("sample", true);
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @Order(21)
    public void podPrune() {
        HttpResponse<Void> httpResponse = podmanPodService.podPrune();
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }
}
