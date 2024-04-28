package ir.moke.jpodman;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ContainerTest {

    @Test
    public void runContainer() {
        String name = "test";
        String imageName = "docker.io/library/postgres";
        String hostname = "psql";
        String dns = "8.8.8.8,4.2.2.2";
        String searchDomain = "google.com";
        String timeZone = "UTC";
        String ip = "10.0.2.12";
        List<String> exposePorts = List.of("8080", "80", "2222");
        Map<String,String> publishPorts = Map.of("192.168.1.100:8080","8080","2280","2333","4343","80");
        Map<String,String> hosts = Map.of("linux","192.168.1.1","google","80.220.12.12");
        Map<String,String> environments = Map.of("name","ali","age","22");
        Map<String,String> volumes = Map.of("sample1","/target");
        ContainerManager.run(name,
                hostname,
                dns,
                searchDomain,
                true,
                true,
                null,
                timeZone,
                null,//TODO, implement network api
                exposePorts,
                publishPorts,
                true,
                hosts,
                environments,
                volumes,
                imageName);
    }
}
