package ir.moke.jpodman.pojo;

public enum Protocol {
    TCP("tcp"),
    UDP("udp"),
    SCTP("sctp");

    private final String value;
    Protocol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
