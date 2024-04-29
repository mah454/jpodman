package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PodInfo {
    @JsonProperty("Cgroup")
    private String cgroup;
    @JsonProperty("Containers")
    private List<ContainerInfo> containers;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("InfraId")
    private String infraId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Namespace")
    private String namespace;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Labels")
    private Map<String, String> labels;
    @JsonProperty("Networks")
    private List<NetworkInfo> networks;

    public String getCgroup() {
        return cgroup;
    }

    public void setCgroup(String cgroup) {
        this.cgroup = cgroup;
    }

    public List<ContainerInfo> getContainers() {
        return containers;
    }

    public void setContainers(List<ContainerInfo> containers) {
        this.containers = containers;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfraId() {
        return infraId;
    }

    public void setInfraId(String infraId) {
        this.infraId = infraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public List<NetworkInfo> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkInfo> networks) {
        this.networks = networks;
    }
}
