package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Port {
    @JsonProperty("host_port")
    private Long hostPort;
    @JsonProperty("container_port")
    private Long containerPort;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("host_ip")
    private String hostIp;
    @JsonProperty("range")
    private Long range;

    public Long getHostPort() {
        return hostPort;
    }

    public void setHostPort(Long hostPort) {
        this.hostPort = hostPort;
    }

    public Long getContainerPort() {
        return containerPort;
    }

    public void setContainerPort(Long containerPort) {
        this.containerPort = containerPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }
}
