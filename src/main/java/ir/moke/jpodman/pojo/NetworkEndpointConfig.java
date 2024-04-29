package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NetworkEndpointConfig {
    @JsonProperty("Aliases")
    private String aliases;
    @JsonProperty("EndpointID")
    private String endpointId;
    @JsonProperty("Gateway")
    private String gateway;
    @JsonProperty("IPAddress")
    private String ipAddress;
    @JsonProperty("IPPrefixLen")
    private Integer ipPrefixLen;
    @JsonProperty("Links")
    private List<String> links;
    @JsonProperty("MacAddress")
    private String macAddress;
    @JsonProperty("NetworkID")
    private String networkId;

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getIpPrefixLen() {
        return ipPrefixLen;
    }

    public void setIpPrefixLen(Integer ipPrefixLen) {
        this.ipPrefixLen = ipPrefixLen;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
}
