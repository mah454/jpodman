package ir.moke.jpodman.pojo;

public enum RestartPolicy {
    ALWAYS("always"),
    NO("no"),
    ON_FAILURE("on-failure"),
    UNLESS_STOPPED("unless-stopped");

    private final String value;

    RestartPolicy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
