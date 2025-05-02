package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.kafir.annotation.*;

import java.net.http.HttpResponse;

public interface PodmanVolumeService {

    @DELETE("volumes/{name}")
    HttpResponse<Void> volumeRemove(@PathParameter("name") String name, @QueryParameter("force") boolean force);

    @GET("volumes/{name}/json")
    HttpResponse<String> volumeInspect(@PathParameter("name") String name);

    @POST("volumes/create")
    HttpResponse<String> volumeCreate(Volume volume);

    @GET("volumes/json")
    HttpResponse<String> volumeList();

    @POST("volumes/prune")
    HttpResponse<Void> volumePrune();
}
