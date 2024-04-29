package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Subnet {
    @JsonProperty("subnet")
    private String subnet;
    @JsonProperty("gateway")
    private String gateway;

    public Subnet() {
    }

    public Subnet(String subnet, String gateway) {
        this.subnet = subnet;
        this.gateway = gateway;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
