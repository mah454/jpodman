package ir.moke.jpodman.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PodStats {
    @JsonProperty("CPU")
    private String cpu;
    @JsonProperty("MemUsage")
    private String memUsage;
    @JsonProperty("MemUsageBytes")
    private String memUsageBytes;
    @JsonProperty("Mem")
    private String mem;
    @JsonProperty("NetIO")
    private String netIO;
    @JsonProperty("BlockIO")
    private String blockIO;
    @JsonProperty("PIDS")
    private String pids;
    @JsonProperty("Pod")
    private String pod;
    @JsonProperty("CID")
    private String cid;
    @JsonProperty("Name")
    private String name;

    public String getCpu() {
        return cpu;
    }

    public String getMemUsage() {
        return memUsage;
    }

    public String getMemUsageBytes() {
        return memUsageBytes;
    }

    public String getMem() {
        return mem;
    }

    public String getNetIO() {
        return netIO;
    }

    public String getBlockIO() {
        return blockIO;
    }

    public String getPids() {
        return pids;
    }

    public String getPod() {
        return pod;
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }
}
