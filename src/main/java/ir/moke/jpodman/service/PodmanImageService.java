package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanImageService {

    @POST
    @Path("images/pull")
    Response imagePull(@QueryParam("reference") String reference);

    @GET
    @Path("images/json")
    List<Image> imageList(@QueryParam("all") @DefaultValue("false") boolean all);

    @GET
    @Path("images/search")
    List<SearchImage> imageSearch(@QueryParam("term") String term);

    @DELETE
    @Path("images/remove")
    Response imageRemove(@QueryParam("images") List<String> images,
                         @QueryParam("all") @DefaultValue("true") boolean all,
                         @QueryParam("force") @DefaultValue("false") boolean force);

    @GET
    @Path("images/{name}/json")
    Response imageInspect(@PathParam("name") String name);

    @GET
    @Path("images/{name}/exists")
    Response imageExists(@PathParam("name") String name);

    @POST
    @Path("images/{name}/untag")
    Response imageUntag(@PathParam("name") String name);


    @GET
    @Path("images/prune")
    Response imagePrune();
}
