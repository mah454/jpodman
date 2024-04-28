package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Volume;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanVolumeService {

    @DELETE
    @Path("volumes/{name}")
    Response volumeRemove(@PathParam("name") String name);

    @GET
    @Path("volumes/{name}/json")
    Response volumeInspect(@PathParam("name") String name);

    @POST
    @Path("volumes/create")
    Volume volumeCreate(Volume volume);

    @GET
    @Path("volumes/json")
    List<Volume> volumeList();

    @POST
    @Path("volumes/prune")
    Response volumePrune();
}
