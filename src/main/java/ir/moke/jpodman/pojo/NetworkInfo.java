package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class NetworkInfo {
    private String name;
    private String id;
    private String driver;
    private String network_interface;
    private String created;
    private List<Subnet> subnets;
    private Boolean internal;
    @JsonProperty("dns_enabled")
    private Boolean dnsEnabled;
    @JsonProperty("ipam_options")
    private Map<String, String> ipamOptions;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDriver() {
        return driver;
    }

    public String getNetwork_interface() {
        return network_interface;
    }

    public String getCreated() {
        return created;
    }

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public Boolean getInternal() {
        return internal;
    }

    public Boolean getDnsEnabled() {
        return dnsEnabled;
    }

    public Map<String, String> getIpamOptions() {
        return ipamOptions;
    }
}
