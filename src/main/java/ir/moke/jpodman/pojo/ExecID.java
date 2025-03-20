package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExecID(@JsonProperty("Id") String id) {
}
