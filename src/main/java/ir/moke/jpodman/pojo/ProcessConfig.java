package ir.moke.jpodman.pojo;

public record ProcessConfig(String arguments, String entrypoint, String privileged, String tty, String user) {
}
