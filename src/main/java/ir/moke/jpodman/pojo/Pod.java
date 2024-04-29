package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Pod {
    @JsonProperty("name")
    private String name;
    @JsonProperty("dns_option")
    private List<String> dnsOption;
    @JsonProperty("dns_search")
    private List<String> dnsSearch;
    @JsonProperty("dns_server")
    private List<String> dnsServer;
    @JsonProperty("hostadd")
    private List<String> hosts;
    @JsonProperty("labels")
    private Map<String, String> labels;
    @JsonProperty("portmappings")
    private List<PortMapping> portMappings;
    @JsonProperty("static_ip")
    private String staticIp;
    @JsonProperty("static_mac")
    private String staticMac;
    @JsonProperty("no_infra")
    private Boolean noInfra;
    @JsonProperty("infra_command")
    private List<String> infraCommands;
    @JsonProperty("infra_image")
    private String infraImage;
    @JsonProperty("no_manage_hosts")
    private Boolean noManageHosts;
    @JsonProperty("no_manage_resolv_conf")
    private Boolean noManageResolvConf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDnsOption() {
        return dnsOption;
    }

    public void setDnsOption(List<String> dnsOption) {
        this.dnsOption = dnsOption;
    }

    public List<String> getDnsSearch() {
        return dnsSearch;
    }

    public void setDnsSearch(List<String> dnsSearch) {
        this.dnsSearch = dnsSearch;
    }

    public List<String> getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(List<String> dnsServer) {
        this.dnsServer = dnsServer;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    public void setPortMappings(List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }

    public String getStaticMac() {
        return staticMac;
    }

    public void setStaticMac(String staticMac) {
        this.staticMac = staticMac;
    }

    public Boolean getNoInfra() {
        return noInfra;
    }

    public void setNoInfra(Boolean noInfra) {
        this.noInfra = noInfra;
    }

    public List<String> getInfraCommands() {
        return infraCommands;
    }

    public void setInfraCommands(List<String> infraCommands) {
        this.infraCommands = infraCommands;
    }

    public String getInfraImage() {
        return infraImage;
    }

    public void setInfraImage(String infraImage) {
        this.infraImage = infraImage;
    }

    public Boolean getNoManageHosts() {
        return noManageHosts;
    }

    public void setNoManageHosts(Boolean noManageHosts) {
        this.noManageHosts = noManageHosts;
    }

    public Boolean getNoManageResolvConf() {
        return noManageResolvConf;
    }

    public void setNoManageResolvConf(Boolean noManageResolvConf) {
        this.noManageResolvConf = noManageResolvConf;
    }
}
