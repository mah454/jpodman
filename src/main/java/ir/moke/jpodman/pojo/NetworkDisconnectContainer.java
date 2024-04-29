package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkDisconnectContainer {
    @JsonProperty("Container")
    private String name;
    @JsonProperty("Force")
    private Boolean force;

    public NetworkDisconnectContainer() {
    }

    public NetworkDisconnectContainer(String name, Boolean force) {
        this.name = name;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }
}
