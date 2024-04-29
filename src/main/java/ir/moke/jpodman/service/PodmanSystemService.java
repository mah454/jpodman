package ir.moke.jpodman.service;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanSystemService {

    @GET
    @Path("_ping")
    Response ping() ;

    @GET
    @Path("events")
    Response events(@QueryParam("stream") @DefaultValue("true") boolean stream) ;

    @GET
    @Path("info")
    Response info();

    @GET
    @Path("system/df")
    Response df();

    @POST
    @Path("system/prune")
    Response prune();




}
