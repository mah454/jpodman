package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Volume {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("Mountpoint")
    private String mountPoint;
    @JsonProperty("CreatedAt")
    private String createdAt;
    @JsonProperty("Labels")
    private Map<String, String> labels;
    @JsonProperty("Scope")
    private String scope;
    @JsonProperty("Options")
    private Map<String, String> options;
    @JsonProperty("Anonymous")
    private Boolean anonymous;
    @JsonProperty("MountCount")
    private Integer mountCount;
    @JsonProperty("NeedsCopyUp")
    private Boolean needsCopyUp;
    @JsonProperty("NeedsChown")
    private Boolean needsChown;
    @JsonProperty("LockNumber")
    private Integer lockNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getScope() {
        return scope;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public Integer getMountCount() {
        return mountCount;
    }

    public Boolean getNeedsCopyUp() {
        return needsCopyUp;
    }

    public Boolean getNeedsChown() {
        return needsChown;
    }

    public Integer getLockNumber() {
        return lockNumber;
    }
}
