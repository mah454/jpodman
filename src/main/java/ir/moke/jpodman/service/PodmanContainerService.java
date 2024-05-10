package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Container;
import ir.moke.jpodman.pojo.ContainerInfo;
import ir.moke.kafir.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PodmanContainerService {

    @GET("containers/{name}/exists")
    HttpResponse<Void> containerExists(@PathParameter("name") String name);

    @GET("containers/{name}/healthcheck")
    HttpResponse<String> containerHealthCheck(@PathParameter("name") String name);

    @DELETE("containers/{name}")
    HttpResponse<Void> containerDelete(@PathParameter("name") String name,
                                       @QueryParameter("force") boolean force,
                                       @QueryParameter("v") boolean deleteVolumes);

    @GET("containers/{name}/json")
    String containerInspect(@PathParameter("name") String name);

    @POST("containers/{name}/kill")
    HttpResponse<Void> containerKill(@PathParameter("name") String name);

    @POST("containers/{name}/pause")
    HttpResponse<Void> containerPause(@PathParameter("name") String name);

    @POST("containers/{name}/unpause")
    HttpResponse<Void> containerUnpause(@PathParameter("name") String name);


    /**
     * @param name       the name or ID of the container
     * @param flow       Keep connection after returning logs.
     * @param stdout     Return logs from stdout
     * @param stderr     Return logs from stderr
     * @param timestamps Add timestamps to every log line
     * @param tail       Only return this number of log lines from the end of the logs
     * @param since      Only return logs since this time, as a UNIX timestamp
     * @param until      Only return logs before this time, as a UNIX timestamp
     * @return {@link HttpResponse}
     */
    @GET("containers/{name}/logs")
    HttpResponse<String> containerLogs(@PathParameter("name") String name,
                                       @QueryParameter("flow") Boolean flow,
                                       @QueryParameter("stdout") Boolean stdout,
                                       @QueryParameter("stderr") Boolean stderr,
                                       @QueryParameter("timestamps") boolean timestamps,
                                       @QueryParameter("tail") String tail,
                                       @QueryParameter("since") String since,
                                       @QueryParameter("until") String until);

    @POST("containers/{name}/rename")
    void containerRename(@PathParameter("name") String name, @QueryParameter("name") String newName);

    @POST("containers/{name}/restart")
    void containerRestart(@PathParameter("name") String name);

    @POST("containers/{name}/start")
    HttpResponse<String> containerStart(@PathParameter("name") String name);

    @GET("containers/{name}/stats")
    HttpResponse<String> containerStats(@PathParameter("name") String name);

    @POST("containers/{name}/stop")
    HttpResponse<Void> containerStop(@PathParameter("name") String name, @PathParameter("t") Integer waitTime);

    @GET("containers/{name}/top")
    HttpResponse<String> containerListProcesses(@PathParameter("name") String name);

    @POST("containers/create")
    HttpResponse<String> containerCreate(Container container);

    @POST("containers/create")
    CompletableFuture<HttpResponse<String>> containerCreateAsync(Container container);

    @GET("containers/json")
    List<ContainerInfo> containerList(@QueryParameter("all") Boolean all, @QueryParameter("pod") Boolean pod);

    @POST("containers/prune")
    void containerPrune();

}
