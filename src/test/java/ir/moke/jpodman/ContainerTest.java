package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.pojo.NetworkDriver;
import ir.moke.jpodman.pojo.Subnet;
import ir.moke.jpodman.service.PodmanContainerService;
import ir.moke.jpodman.service.PodmanImageService;
import ir.moke.jpodman.service.PodmanNetworkService;
import ir.moke.jpodman.service.PodmanVolumeService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContainerTest {
    private static final String IMAGE_NAME = "postgres:latest";
    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanSSE podmanSSE = new PodmanSSE("127.0.0.1", 9000);
    private static final PodmanContainerService podmanContainerService = podman.api(PodmanContainerService.class);
    private static final PodmanVolumeService podmanVolumeService = podman.api(PodmanVolumeService.class);
    private static final PodmanImageService podmanImageService = podman.api(PodmanImageService.class);
    private static final PodmanNetworkService podmanNetworkService = podman.api(PodmanNetworkService.class);

    // TODO: fix me
    /*@Test
    @Order(0)
    public void containerCreate() {
        containerDelete();
        HttpResponse<String> volumeListResponse = podmanVolumeService.volumeList();

        currentVolumes.stream()
                .filter(item -> item.getName().equals("sample-volume"))
                .findFirst()
                .map(Volume::getName)
                .ifPresent(ContainerTest::removeTestVolume);

        HttpResponse<Void> response = podmanImageService.imageExists(IMAGE_NAME);

        if (response.statusCode() != 204) {
            HttpResponse<String> pullResponse = podmanImageService.imagePull(IMAGE_NAME);
            Assertions.assertEquals(200, pullResponse.statusCode());
            System.out.println(pullResponse.body());
        }

        Network network = createNetwork();

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
//        container.setNetworks(Map.of(network.getName(), new NetworkConnect("20.20.20.111")));
//        container.setNetns(Map.of("nsmode", network.getDriver().getType()));

//        PortMapping portMapping = new PortMapping();
//        portMapping.setContainerPort(8080);
//        portMapping.setHostPort(8080);
//        portMapping.setHostIp("192.168.1.1");
//        portMapping.setRange(10);
//        portMapping.setProtocol(Protocol.TCP);
//        container.setPortMappings(List.of(portMapping));

//        container.setPortMappings(List.of(new PortMapping(8080, 8081), new PortMapping(2121, 9091, Protocol.UDP)));

//        container.setStaticIp("172.16.10.10"); // need run as root
//        container.setUseHostEnvironments(true);
//        container.setEntrypoint(List.of("/bin/sh"));

        HttpResponse<String> containerCreateResponse = podmanContainerService.containerCreate(container);
        System.out.println(containerCreateResponse.statusCode());
        System.out.println(containerCreateResponse.body());
        Assertions.assertEquals(201, containerCreateResponse.statusCode());
        deleteNetwork();
        removeTestVolume("sample-volume");
    }*/

    @Test
    @Order(1)
    public void containerExists() {
        boolean exists = checkContainerExists();
        Assertions.assertTrue(exists);
    }

    @Test
    @Order(2)
    public void containerList() {
        HttpResponse<String> response = podmanContainerService.containerList(true, null);
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.body());
    }

    @Test
    @Order(3)
    public void containerStart() {
        HttpResponse<String> httpResponse = podmanContainerService.containerStart("test");
        System.out.println(httpResponse.body());
        Assertions.assertEquals(204, httpResponse.statusCode());

    }

    @Test
    @Order(4)
    public void containerState() {
        podmanSSE.containerStats("test", 4, Assertions::assertNotNull);
    }

    @Test
    @Order(5)
    public void containerTop() {
        podmanSSE.containerTop("test", 4, Assertions::assertNotNull);
    }

    @Test
    @Order(6)
    public void containerStop() {
        HttpResponse<Void> httpResponse = podmanContainerService.containerStop("test", 10);
        Assertions.assertEquals(204, httpResponse.statusCode());
    }

    @Test
    @Order(7)
    public void containerDelete() {
        if (checkContainerExists()) {
            HttpResponse<Void> httpResponse = podmanContainerService.containerDelete("test", true, true);
            Assertions.assertEquals(200, httpResponse.statusCode());
        }
    }

    private static void removeTestVolume(String name) {
        HttpResponse<Void> httpResponse = podmanVolumeService.volumeRemove(name, false);
        System.out.println(httpResponse.statusCode());
    }

    private static boolean checkContainerExists() {
        HttpResponse<Void> httpResponse = podmanContainerService.containerExists("test");
        return httpResponse.statusCode() == 204;
    }

    public static void deleteNetwork() {
        HttpResponse<String> response = podmanNetworkService.networkList();
        Assertions.assertEquals(200, response.statusCode());
        // TODO: fix me
        /*for (NetworkInfo networkInfo : networkInfoList) {
            if (networkInfo.getName().equals("n1")) {
                HttpResponse<Void> httpResponse = podmanNetworkService.networkRemove("n1", true);
                Assertions.assertEquals(200, httpResponse.statusCode());
            }
        }*/
    }

    public static Network createNetwork() {
        deleteNetwork();
        Network network = new Network();
        network.setDriver(NetworkDriver.BRIDGE);
        network.setName("n1");
        network.setGateway("20.20.20.1");
        network.setSubnets(List.of(new Subnet("20.20.20.0/24", "20.20.20.1")));

        HttpResponse<String> httpResponse = podmanNetworkService.networkCreate(network);
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
        return network;
    }
}
