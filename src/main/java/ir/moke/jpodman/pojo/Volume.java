package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Volume {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("IgnoreIfExists")
    private Boolean ignoreIfExists;
    @JsonProperty("Label")
    private Map<String, String> label;
    @JsonProperty("Labels")
    private Map<String, String> labels;
    @JsonProperty("Options")
    private Map<String, String> options;

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

    public Boolean getIgnoreIfExists() {
        return ignoreIfExists;
    }

    public void setIgnoreIfExists(Boolean ignoreIfExists) {
        this.ignoreIfExists = ignoreIfExists;
    }

    public Map<String, String> getLabel() {
        return label;
    }

    public void setLabel(Map<String, String> label) {
        this.label = label;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
