package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Pod;
import ir.moke.jpodman.pojo.PodInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanPodService {

    @GET
    @Path("generate/kube")
    Response generateKube();

    @POST
    @Path("play/kube")
    Response playKube(@QueryParam("network") String network, @QueryParam("start") Boolean start);

    @DELETE
    @Path("pods/{name}")
    Response podRemove(@PathParam("name") String name, @QueryParam("force") Boolean force);

    @GET
    @Path("pods/{name}/exists")
    Response podExists(@PathParam("name") String name);

    @GET
    @Path("pods/{name}/json")
    Response podInspect(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/kill")
    Response podKill(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/pause")
    Response podPause(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/unpause")
    Response podUnpause(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/restart")
    Response podRestart(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/start")
    Response podStart(@PathParam("name") String name);

    @POST
    @Path("pods/{name}/stop")
    Response podStop(@PathParam("name") String name);

    @GET
    @Path("pods/{name}/top")
    Response podTop(@PathParam("name") String name, @PathParam("stream") Boolean stream);

    @GET
    @Path("pods/json")
    List<PodInfo> podList();

    @POST
    @Path("pods/prune")
    Response podPrune();

    @GET
    @Path("pods/stats")
    Response podStats(@QueryParam("all") boolean all, @QueryParam("namesOrIDs") List<String> namesOrIDs);

    @POST
    @Path("pods/create")
    Response podCreate(Pod pod);
}