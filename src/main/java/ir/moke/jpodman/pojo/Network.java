package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Network {
    @JsonProperty(namespace = "DisableDNS")
    private Boolean disableDNS;
    @JsonProperty(namespace = "Driver")
    private String driver;
    @JsonProperty(namespace = "Gateway")
    private String gateway;
    @JsonProperty(namespace = "Internal")
    private Boolean internal;
    @JsonProperty(namespace = "Labels")
    private Map<String, String> labels;
    @JsonProperty(namespace = "MacVLAN")
    private String macVLAN;
    @JsonProperty(namespace = "Options")
    private Map<String, String> options;
    @JsonProperty(namespace = "Range")
    private IPNet range;
    @JsonProperty(namespace = "Subnet")
    private IPNet subnet;

    public Boolean getDisableDNS() {
        return disableDNS;
    }

    public void setDisableDNS(Boolean disableDNS) {
        this.disableDNS = disableDNS;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getMacVLAN() {
        return macVLAN;
    }

    public void setMacVLAN(String macVLAN) {
        this.macVLAN = macVLAN;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public IPNet getRange() {
        return range;
    }

    public void setRange(IPNet range) {
        this.range = range;
    }

    public IPNet getSubnet() {
        return subnet;
    }

    public void setSubnet(IPNet subnet) {
        this.subnet = subnet;
    }
}
