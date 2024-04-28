package ir.moke.jpodman.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanVolumeService {

    @DELETE
    @Path("volumes/{name}")
    Response volumeRemove(@QueryParam("name") String name);

    @GET
    @Path("volumes/{name}/json")
    Response volumeInspect(@QueryParam("name") String name);

    @POST
    @Path("volumes/create")
    Response volumeCreate(@QueryParam("Driver") String driver,
                          @QueryParam("Label") Map<String,String> label,
                          @QueryParam("Name") String name,
                          @QueryParam("Options") Map<String, String> options);

    @GET
    @Path("volumes/json")
    Response volumeList();

    @POST
    @Path("volumes/prune")
    Response volumePrune();
}
