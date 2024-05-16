package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Image;
import ir.moke.jpodman.pojo.SearchImage;
import ir.moke.kafir.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PodmanImageService {

    @POST("images/pull")
    HttpResponse<String> imagePull(@QueryParameter("reference") String reference);

    @POST("images/pull")
    CompletableFuture<HttpResponse<String>> imagePullAsync(@QueryParameter("reference") String reference);

    @GET("images/json")
    List<Image> imageList(@QueryParameter("all") boolean all);

    @GET("images/search")
    List<SearchImage> imageSearch(@QueryParameter("term") String term);

    @GET("images/search")
    HttpResponse<String> imageSearch2(@QueryParameter("term") String term);

    @DELETE("images/remove")
    HttpResponse<Void> imageRemove(@QueryParameter("images") List<String> images, @QueryParameter("all") boolean all, @QueryParameter("force") boolean force);

    @GET("images/{name}/json")
    HttpResponse<Image> imageInspect(@PathParameter("name") String name);

    @GET("images/{name}/exists")
    HttpResponse<Void> imageExists(@PathParameter("name") String name);

    @POST("images/{name}/untag")
    HttpResponse<String> imageUntag(@PathParameter("name") String name);


    @POST("images/prune")
    HttpResponse<String> imagePrune();
}
