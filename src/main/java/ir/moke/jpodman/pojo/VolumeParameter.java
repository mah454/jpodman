package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolumeParameter {
    @JsonProperty("Dest")
    private String dest;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Options")
    private String options;

    public VolumeParameter() {
    }

    public VolumeParameter(String dest, String name) {
        this.dest = dest;
        this.name = name;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
