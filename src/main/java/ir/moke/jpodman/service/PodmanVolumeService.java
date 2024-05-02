package ir.moke.jpodman.service;

import ir.moke.jpodman.annotation.*;
import ir.moke.jpodman.pojo.Volume;

import java.net.http.HttpResponse;
import java.util.List;

public interface PodmanVolumeService {

    @DELETE("volumes/{name}")
    HttpResponse<Void> volumeRemove(@PathParameter("name") String name, @QueryParameter("force") boolean force);

    @GET("volumes/{name}/json")
    String volumeInspect(@PathParameter("name") String name);

    @POST("volumes/create")
    Volume volumeCreate(Volume volume);

    @GET("volumes/json")
    List<Volume> volumeList();

    @POST("volumes/prune")
    HttpResponse<Void> volumePrune();
}
