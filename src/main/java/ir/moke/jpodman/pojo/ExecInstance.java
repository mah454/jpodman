package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.kafir.utils.JsonUtils;

import java.util.List;

public record ExecInstance(@JsonProperty("AttachStdin") boolean attachStdin,
                           @JsonProperty("AttachStdout") boolean attachStdout,
                           @JsonProperty("AttachStderr") boolean attachStderr,
                           @JsonProperty("Tty") boolean tty,
                           @JsonProperty("User") String user,
                           @JsonProperty("Privileged") Boolean privileged,
                           @JsonProperty("WorkingDir") String workingDir,
                           @JsonProperty("Env") List<String> env,
                           @JsonProperty("DetachKeys") String detachKeys,
                           @JsonProperty("Cmd") List<String> cmd) {
    public ExecInstance(boolean attachStdin, boolean attachStdout, boolean attachStderr, boolean tty, List<String> cmd) {
        this(attachStdin, attachStdout, attachStderr, tty, "", null, null, List.of(), null, cmd);
    }

    public ExecInstance(List<String> cmd) {
        this(true, true, true, true, null, null, null, List.of(), null, cmd);
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }
}
