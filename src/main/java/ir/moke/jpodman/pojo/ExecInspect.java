package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExecInspect(@JsonProperty("CanRemove") String canRemove,
                          @JsonProperty("ContainerID") String containerID,
                          @JsonProperty("DetachKeys") String detachKeys,
                          @JsonProperty("ExitCode") String exitCode,
                          @JsonProperty("ID") String iD,
                          @JsonProperty("OpenStderr") String openStderr,
                          @JsonProperty("OpenStdin") String openStdin,
                          @JsonProperty("OpenStdout") String openStdout,
                          @JsonProperty("Pid") String pid,
                          @JsonProperty("ProcessConfig") ProcessConfig processConfig,
                          @JsonProperty("Running") String running) {
}
