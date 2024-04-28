package ir.moke.jpodman.pojo;

public class Port {
    private Long hostPort;
    private Long containerPort;
    private String protocol;
    private String hostIp;

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
}
