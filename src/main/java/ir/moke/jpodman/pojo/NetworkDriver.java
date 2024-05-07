package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NetworkDriver {
    BRIDGE("bridge"),
    MAC_VLAN("macvlan"),
    IP_VLAN("ipvlan");

    private final String type;

    NetworkDriver(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static NetworkDriver fromValue(String value) {
        return NetworkDriver.fromValue(value);
    }
}
