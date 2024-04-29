package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkConnectContainer {
    @JsonProperty("Container")
    private String name;
    @JsonProperty("EndpointConfig")
    private NetworkEndpointConfig endpointConfig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkEndpointConfig getEndpointConfig() {
        return endpointConfig;
    }

    public void setEndpointConfig(NetworkEndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;
    }
}
