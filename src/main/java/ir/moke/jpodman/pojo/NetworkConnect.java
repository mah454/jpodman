package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NetworkConnect {
    @JsonProperty("static_ips")
    private List<String> staticIps;
    @JsonProperty("static_mac")
    private String staticMac;
    @JsonProperty("interface_name")
    private String interfaceName;

    public NetworkConnect() {
    }

    public NetworkConnect(List<String> staticIps, String staticMac, String interfaceName) {
        this.staticIps = staticIps;
        this.staticMac = staticMac;
        this.interfaceName = interfaceName;
    }

    public NetworkConnect(List<String> staticIps) {
        this.staticIps = staticIps;
    }

    public NetworkConnect(String ip) {
        this.staticIps = List.of(ip);
    }

    public NetworkConnect(String ip, String staticMac) {
        this.staticIps = List.of(ip);
        this.staticMac = staticMac;
    }

    public List<String> getStaticIps() {
        return staticIps;
    }

    public void setStaticIps(List<String> staticIps) {
        this.staticIps = staticIps;
    }

    public String getStaticMac() {
        return staticMac;
    }

    public void setStaticMac(String staticMac) {
        this.staticMac = staticMac;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
