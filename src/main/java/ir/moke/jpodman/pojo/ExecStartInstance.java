package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.kafir.utils.JsonUtils;

public record ExecStartInstance(@JsonProperty("Detach") boolean detach,
                                @JsonProperty("Tty") boolean tty,
                                @JsonProperty("h") Integer height,
                                @JsonProperty("w") Integer width) {
    public ExecStartInstance(boolean detach, boolean tty) {
        this(detach, tty, null, null);
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }
}
