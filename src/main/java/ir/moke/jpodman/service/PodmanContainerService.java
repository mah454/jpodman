package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Container;
import ir.moke.jpodman.pojo.ContainerInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanContainerService {

    @GET
    @Path("containers/{name}/exists")
    Response containerExists(@PathParam("name") String name);

    @GET
    @Path("containers/{name}/healthcheck")
    Response containerHealthCheck(@PathParam("name") String name);

    @DELETE
    @Path("containers/{name}")
    Response containerDelete(@PathParam("name") String name,
                             @QueryParam("force") @DefaultValue("false") boolean force,
                             @QueryParam("v") @DefaultValue("false") boolean deleteVolumes);

    @GET
    @Path("containers/{name}/json")
    Response containerInspect(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/kill")
    Response containerKill(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/pause")
    Response containerPause(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/unpause")
    Response containerUnpause(@PathParam("name") String name);


    /**
     * @param name       the name or ID of the container
     * @param flow       Keep connection after returning logs.
     * @param stdout     Return logs from stdout
     * @param stderr     Return logs from stderr
     * @param timestamps Add timestamps to every log line
     * @param tail       Only return this number of log lines from the end of the logs
     * @param since      Only return logs since this time, as a UNIX timestamp
     * @param until      Only return logs before this time, as a UNIX timestamp
     * @return {@link Response}
     */
    @GET
    @Path("containers/{name}/logs")
    Response containerLogs(@PathParam("name") String name,
                           @QueryParam("flow") Boolean flow,
                           @QueryParam("stdout") Boolean stdout,
                           @QueryParam("stderr") Boolean stderr,
                           @QueryParam("timestamps") boolean timestamps,
                           @QueryParam("tail") String tail,
                           @QueryParam("since") String since,
                           @QueryParam("until") String until);

    @POST
    @Path("containers/{name}/rename")
    Response containerRename(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/restart")
    Response containerRestart(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/start")
    Response containerStart(@PathParam("name") String name);

    @GET
    @Path("containers/{name}/stats")
    Response containerStats(@PathParam("name") String name);

    @POST
    @Path("containers/{name}/stop")
    Response containerStop(@PathParam("name") String name, @PathParam("t") Integer waitTime);

    @GET
    @Path("containers/{name}/top")
    Response containerListProcesses(@PathParam("name") String name);


    @POST
    @Path("containers/create")
    Response containerCreate(Container container);

    @GET
    @Path("containers/json")
    List<ContainerInfo> containerList(@PathParam("all") Boolean all, @QueryParam("pod") Boolean pod);

    @POST
    @Path("containers/prune")
    Response containerPrune();

    @GET
    @Path("generate/kube")
    Response generateKube();

    @POST
    @Path("play/kube")
    Response playKube(@PathParam("network") String network, @PathParam("start") Boolean start);


}
