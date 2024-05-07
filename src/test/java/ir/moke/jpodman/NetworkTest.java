package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.pojo.NetworkDriver;
import ir.moke.jpodman.pojo.NetworkInfo;
import ir.moke.jpodman.pojo.Subnet;
import ir.moke.jpodman.service.PodmanNetworkService;
import org.junit.jupiter.api.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkTest {

    private static final Podman podman = new Podman("127.0.0.1", 9000);
    private static final PodmanNetworkService podmanNetworkService = podman.api(PodmanNetworkService.class);

    @Test
    @Order(0)
    public void networkCreate() {
        Network network = new Network();
        network.setName("sample");
        network.setLabels(Map.of("NAME", "AAA", "AGE", "222"));
        network.setSubnets(List.of(new Subnet("10.10.10.0/24", "10.10.10.1")));
        network.setDriver(NetworkDriver.BRIDGE);

        HttpResponse<String> httpResponse = podmanNetworkService.networkCreate(network);
        System.out.println(httpResponse.body());
        Assertions.assertEquals(200, httpResponse.statusCode());
        System.out.println(httpResponse.body());
    }

    @Test
    @Order(1)
    public void networkList() {
        List<NetworkInfo> networkInfoList = podmanNetworkService.networkList();
        Assertions.assertFalse(networkInfoList.isEmpty());
        for (NetworkInfo networkInfo : networkInfoList) {
            System.out.println(networkInfo.getNetworkInterface());
        }
    }

    @Test
    @Order(2)
    public void networkDelete() {
        HttpResponse<Void> httpResponse = podmanNetworkService.networkRemove("sample", true);
        System.out.println(httpResponse.statusCode());
    }
}
