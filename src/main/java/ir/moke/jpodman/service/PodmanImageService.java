package ir.moke.jpodman.service;

import ir.moke.jpodman.annotation.*;
import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;

import java.net.http.HttpResponse;
import java.util.List;

public interface PodmanImageService {

    @POST("images/pull")
    HttpResponse<String> imagePull(@QueryParameter("reference") String reference);

    @GET("images/json")
    List<Image> imageList(@QueryParameter("all") boolean all);

    @GET("images/json")
    HttpResponse<List<Image>> imageListTest(@QueryParameter("all") boolean all);

    @GET("images/search")
    List<SearchImage> imageSearch(@QueryParameter("term") String term);

    @DELETE("images/remove")
    HttpResponse<String> imageRemove(@QueryParameter("images") List<String> images,
                                     @QueryParameter("all") boolean all,
                                     @QueryParameter("force") boolean force);

    @GET("images/{name}/json")
    HttpResponse<String> imageInspect(@PathParameter("name") String name);

    @GET("images/{name}/exists")
    HttpResponse<String> imageExists(@PathParameter("name") String name);

    @POST("images/{name}/untag")
    HttpResponse<String> imageUntag(@PathParameter("name") String name);


    @POST("images/prune")
    HttpResponse<String> imagePrune();
}
