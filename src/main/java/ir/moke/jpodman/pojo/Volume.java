package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.moke.jpodman.utils.JsonUtils;
import ir.moke.jpodman.utils.YamlUtils;

import java.time.LocalDateTime;
import java.util.Map;

public class Volume {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("Mountpoint")
    private String mountPoint;
    @JsonProperty("CreatedAt")
    private LocalDateTime createdAt;
    @JsonProperty("Labels")
    private Map<String, String> labels;
    @JsonProperty("Scope")
    private String scope;
    @JsonProperty("Options")
    private Map<String, String> options;

    public Volume() {
    }

    private Volume(Builder builder) {
        this.name = builder.name;
        this.driver = builder.driver;
        this.labels = builder.labels;
        this.scope = builder.scope;
        this.options = builder.options;
    }

    public String getName() {
        return name;
    }

    public String getDriver() {
        return driver;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public String getScope() {
        return scope;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "name='" + name + '\'' +
                ", driver='" + driver + '\'' +
                ", mountPoint='" + mountPoint + '\'' +
                ", createdAt=" + createdAt +
                ", labels=" + labels +
                ", scope='" + scope + '\'' +
                ", options=" + options +
                '}';
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public String toYaml() {
        return YamlUtils.toYaml(this);
    }

    /* Builder class */
    public static class Builder {
        private String name;
        private String driver;
        private Map<String, String> labels;
        private String scope;
        private Map<String, String> options;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDriver(String driver) {
            this.driver = driver;
            return this;
        }

        public Builder setLabels(Map<String, String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder setScope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder setOptions(Map<String, String> options) {
            this.options = options;
            return this;
        }

        public Volume build() {
            return new Volume(this);
        }
    }
}
