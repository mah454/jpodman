package ir.moke.jpodman.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

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
    Response containerDelete(@PathParam("name") String name);

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


    @GET
    @Path("containers/{name}/logs")
    Response containerLogs(@PathParam("name") String name);

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
    Response containerStats(@PathParam("name") String name,
                            @QueryParam("stream") @DefaultValue("true") boolean stream);

    @POST
    @Path("containers/{name}/stop")
    Response containerStop(@PathParam("name") String name, @PathParam("t") Integer waitTime);

    @GET
    @Path("containers/{name}/top")
    Response containerListProcesses(@PathParam("name") String name,
                                    @QueryParam("stream") @DefaultValue("true") boolean stream);


    @POST
    @Path("containers/create")
    Response containerCreate(@PathParam("name") String name,
                             @QueryParam("command") String command,
                             @QueryParam("dns_option") String[] dnsOption,
                             @QueryParam("dns_search") String[] dnsSearch,
                             @QueryParam("dns_server") String[] dnsServer,
                             @QueryParam("entrypoint") String entrypoint,
                             @QueryParam("env") Map<String, String> environments,
                             @QueryParam("env_host") boolean useHostEnvironments,
                             @QueryParam("expose") Map<String, String> expose,
                             @QueryParam("hostAdd") String[] hosts,
                             @QueryParam("hostname") String hostname,
                             @QueryParam("image") String image,
                             @QueryParam("pod") String pod,
                             @QueryParam("privileged") Boolean privileged,
                             @QueryParam("remove") Boolean remove,
                             @QueryParam("user") String user,
                             @QueryParam("work_dir") String workDir,
                             @QueryParam("volumes_from") String[] volumesFrom);

    @GET
    @Path("containers/json")
    Response containerList(@PathParam("all") Boolean all, @QueryParam("pod") Boolean pod);

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
