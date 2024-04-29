package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortMapping {
    @JsonProperty("container_port")
    private int containerPort;
    @JsonProperty("host_ip")
    private String hostIp;
    @JsonProperty("host_port")
    private int hostPort;
    private Protocol protocol;
    private Integer range;

    public PortMapping() {
    }

    public PortMapping(int containerPort, int hostPort) {
        this.containerPort = containerPort;
        this.hostPort = hostPort;
    }

    public PortMapping(int containerPort, int hostPort, Protocol protocol) {
        this.containerPort = containerPort;
        this.hostPort = hostPort;
        this.protocol = protocol;
    }

    public PortMapping(int containerPort, String hostIp, int hostPort, Protocol protocol) {
        this.containerPort = containerPort;
        this.hostIp = hostIp;
        this.hostPort = hostPort;
        this.protocol = protocol;
    }

    public int getContainerPort() {
        return containerPort;
    }

    public void setContainerPort(int containerPort) {
        this.containerPort = containerPort;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
