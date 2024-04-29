package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record IPNet(@JsonProperty("IP") List<String> ip, @JsonProperty("Mask") List<String> mask) {
}
