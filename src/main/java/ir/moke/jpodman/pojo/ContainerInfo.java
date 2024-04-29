package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ContainerInfo {
    @JsonProperty("AutoRemove")
    private Boolean autoRemove;
    @JsonProperty("Command")
    private List<String> command;
    @JsonProperty("postgres")
    private String postgres;
    @JsonProperty("Created")
    private String created;
    @JsonProperty("CreatedAt")
    private String createdAt;
    @JsonProperty("Exited")
    private Boolean exited;
    @JsonProperty("ExitedAt")
    private Long exitedAt;
    @JsonProperty("ExitCode")
    private Long exitCode;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("ImageID")
    private String imageID;
    @JsonProperty("IsInfra")
    private Boolean isInfra;
    @JsonProperty("Labels")
    private List<String> labels;
    @JsonProperty("Mounts")
    private List<String> mounts;
    @JsonProperty("Names")
    private List<String> names;
    @JsonProperty("Namespaces")
    private Map<String, String> namespaces;
    @JsonProperty("Networks")
    private List<String> networks;
    @JsonProperty("Pid")
    private Long pid;
    @JsonProperty("Pod")
    private String pod;
    @JsonProperty("PodName")
    private String podName;
    @JsonProperty("Ports")
    private List<Port> ports;
    @JsonProperty("Size")
    private String size;
    @JsonProperty("StartedAt")
    private Long startedAt;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("CIDFile")
    private String cidFile;

    public Boolean getAutoRemove() {
        return autoRemove;
    }

    public List<String> getCommand() {
        return command;
    }

    public String getPostgres() {
        return postgres;
    }

    public String getCreated() {
        return created;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getExited() {
        return exited;
    }

    public Long getExitedAt() {
        return exitedAt;
    }

    public Long getExitCode() {
        return exitCode;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getImageID() {
        return imageID;
    }

    public Boolean getInfra() {
        return isInfra;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<String> getMounts() {
        return mounts;
    }

    public List<String> getNames() {
        return names;
    }

    public Map<String, String> getNamespaces() {
        return namespaces;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public Long getPid() {
        return pid;
    }

    public String getPod() {
        return pod;
    }

    public String getPodName() {
        return podName;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public String getSize() {
        return size;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public String getState() {
        return state;
    }

    public String getStatus() {
        return status;
    }

    public String getCidFile() {
        return cidFile;
    }
}
