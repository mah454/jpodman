package ir.moke.jpodman.service;

import ir.moke.kafir.annotation.*;
import ir.moke.jpodman.pojo.Pod;
import ir.moke.jpodman.pojo.PodInfo;
import ir.moke.jpodman.pojo.PodStats;

import java.net.http.HttpResponse;
import java.util.List;

public interface PodmanPodService {

    @GET("generate/kube")
    HttpResponse<String> generateKube();

    @POST("play/kube")
    HttpResponse<String> playKube(@QueryParameter("network") String network, @QueryParameter("start") Boolean start);

    @DELETE("pods/{name}")
    HttpResponse<Void> podRemove(@PathParameter("name") String name, @QueryParameter("force") Boolean force);

    @GET("pods/{name}/exists")
    HttpResponse<Void> podExists(@PathParameter("name") String name);

    @GET("pods/{name}/json")
    String podInspect(@PathParameter("name") String name);

    @POST("pods/{name}/kill")
    HttpResponse<Void> podKill(@PathParameter("name") String name);

    @POST("pods/{name}/pause")
    HttpResponse<Void> podPause(@PathParameter("name") String name);

    @POST("pods/{name}/unpause")
    HttpResponse<Void> podUnpause(@PathParameter("name") String name);

    @POST("pods/{name}/restart")
    HttpResponse<Void> podRestart(@PathParameter("name") String name);

    @POST("pods/{name}/start")
    HttpResponse<Void> podStart(@PathParameter("name") String name);

    @POST("pods/{name}/stop")
    HttpResponse<Void> podStop(@PathParameter("name") String name);

    @GET("pods/{name}/top")
    HttpResponse<String> podTop(@PathParameter("name") String name, @PathParameter("stream") Boolean stream);

    @GET("pods/json")
    List<PodInfo> podList();

    @POST("pods/prune")
    HttpResponse<Void> podPrune();

    @GET("pods/stats")
    List<PodStats> podStats(@QueryParameter("all") boolean all, @QueryParameter("namesOrIDs") List<String> namesOrIDs);

    @POST("pods/create")
    HttpResponse<String> podCreate(Pod pod);
}