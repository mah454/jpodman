package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchImage {
    @JsonProperty("Index")
    private String index;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Stars")
    private String stars;
    @JsonProperty("Official")
    private String official;
    @JsonProperty("Automated")
    private String automated;
    @JsonProperty("Tag")
    private String tag;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getAutomated() {
        return automated;
    }

    public void setAutomated(String automated) {
        this.automated = automated;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "SearchImage{" +
                "index='" + index + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stars='" + stars + '\'' +
                ", official='" + official + '\'' +
                ", automated='" + automated + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
