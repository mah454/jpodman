package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Image {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("ParentId")
    private String parentId;
    @JsonProperty("RepoTags")
    private List<String> RepoTags;
    @JsonProperty("RepoDigests")
    private List<String> RepoDigests;
    @JsonProperty("Size")
    private Long size;
    @JsonProperty("SharedSize")
    private Long sharedSize;
    @JsonProperty("VirtualSize")
    private Long virtualSize;
    @JsonProperty("Labels")
    private Map<String, String> labels;
    @JsonProperty("Containers")
    private Long containers;
    @JsonProperty("Names")
    private List<String> names;
    @JsonProperty("Digest")
    private String digest;
    @JsonProperty("History")
    private List<String> history;
    @JsonProperty("Created")
    private Long created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getRepoTags() {
        return RepoTags;
    }

    public void setRepoTags(List<String> repoTags) {
        RepoTags = repoTags;
    }

    public List<String> getRepoDigests() {
        return RepoDigests;
    }

    public void setRepoDigests(List<String> repoDigests) {
        RepoDigests = repoDigests;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getSharedSize() {
        return sharedSize;
    }

    public void setSharedSize(Long sharedSize) {
        this.sharedSize = sharedSize;
    }

    public Long getVirtualSize() {
        return virtualSize;
    }

    public void setVirtualSize(Long virtualSize) {
        this.virtualSize = virtualSize;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Long getContainers() {
        return containers;
    }

    public void setContainers(Long containers) {
        this.containers = containers;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
