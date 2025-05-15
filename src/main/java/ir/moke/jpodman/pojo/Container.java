package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Container {
    private String name;
    private List<String> command;
    @JsonProperty("dns_option")
    private List<String> dnsOption;
    @JsonProperty("dns_search")
    private List<String> dnsSearch;
    @JsonProperty("dns_server")
    private List<String> dnsServer;
    private List<String> entrypoint;
    @JsonProperty("env")
    private Map<String, String> environments;
    @JsonProperty("env_host")
    private boolean useHostEnvironments;
    private Map<Integer, Protocol> expose;
    @JsonProperty("hostadd")
    private List<String> hosts;
    private String hostname;
    private String image;
    private String pod;
    private Boolean privileged;
    private Boolean remove;
    private String user;
    @JsonProperty("work_dir")
    private String workDir;
    @JsonProperty("volumes")
    private List<VolumeParameter> volumes;
    @JsonProperty("static_ip")
    private String staticIp;
    @JsonProperty("portmappings")
    private List<PortMapping> portMappings;
    @JsonProperty("Networks")
    private Map<String, NetworkConnect> networks;
    @JsonProperty("netns")
    private Map<String, String> netns;
    @JsonProperty("terminal")
    private Boolean terminal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
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

    public List<String> getEntrypoint() {
        return entrypoint;
    }

    public void setEntrypoint(List<String> entrypoint) {
        this.entrypoint = entrypoint;
    }

    public Map<String, String> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, String> environments) {
        this.environments = environments;
    }

    public boolean isUseHostEnvironments() {
        return useHostEnvironments;
    }

    public void setUseHostEnvironments(boolean useHostEnvironments) {
        this.useHostEnvironments = useHostEnvironments;
    }

    public Map<Integer, Protocol> getExpose() {
        return expose;
    }

    public void setExpose(Map<Integer, Protocol> expose) {
        this.expose = expose;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Boolean getPrivileged() {
        return privileged;
    }

    public void setPrivileged(Boolean privileged) {
        this.privileged = privileged;
    }

    public Boolean getRemove() {
        return remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    public List<VolumeParameter> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumeParameter> volumes) {
        this.volumes = volumes;
    }

    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }

    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    public void setPortMappings(List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    public Map<String, NetworkConnect> getNetworks() {
        return networks;
    }

    public void setNetworks(Map<String, NetworkConnect> networks) {
        this.networks = networks;
    }

    public Map<String, String> getNetns() {
        return netns;
    }

    public void setNetns(Map<String, String> netns) {
        this.netns = netns;
    }

    public Boolean getTerminal() {
        return terminal;
    }

    public void setTerminal(Boolean terminal) {
        this.terminal = terminal;
    }
}
