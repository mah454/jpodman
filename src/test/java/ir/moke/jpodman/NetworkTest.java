package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.service.PodmanNetworkService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

public class NetworkTest {

    private static final Podman podman = new Podman();
    private static final PodmanNetworkService podmanNetworkService = podman.api(PodmanNetworkService.class);

    @Test
    public void networkCreate() {
        Network network = new Network();
        try (Response response = podmanNetworkService.networkCreate("n1", network)) {
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
        }
    }
}
