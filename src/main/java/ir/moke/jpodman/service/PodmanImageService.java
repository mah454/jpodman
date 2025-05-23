package ir.moke.jpodman.service;

import ir.moke.kafir.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PodmanImageService {

    @POST("images/pull")
    HttpResponse<String> imagePull(@QueryParameter("reference") String reference, @QueryParameter("compatMode") boolean compatMode);

    @POST("images/pull")
    CompletableFuture<HttpResponse<String>> imagePullAsync(@QueryParameter("reference") String reference, @QueryParameter("compatMode") boolean compatMode);

    @GET("images/json")
    HttpResponse<String> imageList(@QueryParameter("all") boolean all);

    @GET("images/search")
    HttpResponse<String> imageSearch(@QueryParameter("term") String term);

    @DELETE("images/remove")
    HttpResponse<Void> imageRemove(@QueryParameter("images") List<String> images, @QueryParameter("all") boolean all, @QueryParameter("force") boolean force);

    @GET("images/{name}/json")
    HttpResponse<String> imageInspect(@PathParameter("name") String name);

    @GET("images/{name}/exists")
    HttpResponse<Void> imageExists(@PathParameter("name") String name);

    @POST("images/{name}/untag")
    HttpResponse<String> imageUntag(@PathParameter("name") String name);


    @POST("images/prune")
    HttpResponse<String> imagePrune();
}
