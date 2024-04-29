package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.pojo.NetworkInfo;
import ir.moke.jpodman.pojo.Subnet;
import ir.moke.jpodman.service.PodmanNetworkService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkTest {

    private static final Podman podman = new Podman();
    private static final PodmanNetworkService podmanNetworkService = podman.api(PodmanNetworkService.class);

    @Test
    @Order(0)
    public void networkCreate() {
        Network network = new Network();
        network.setName("sample");
        network.setLabels(Map.of("NAME", "AAA", "AGE", "222"));
        network.setSubnets(List.of(new Subnet("10.10.10.0/24", "10.10.10.1")));

        NetworkInfo networkInfo = podmanNetworkService.networkCreate(network);
        Assertions.assertEquals("sample", networkInfo.getName());
    }

    @Test
    @Order(1)
    public void networkList() {
        List<NetworkInfo> networkInfoList = podmanNetworkService.networkList();
        Assertions.assertFalse(networkInfoList.isEmpty());
    }

    @Test
    @Order(2)
    public void networkDelete() {
        try (Response response = podmanNetworkService.networkRemove("sample", true)) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
        }
    }
}
