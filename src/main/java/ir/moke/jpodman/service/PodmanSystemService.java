package ir.moke.jpodman.service;

import ir.moke.jpodman.annotation.GET;
import ir.moke.jpodman.annotation.POST;
import ir.moke.jpodman.annotation.QueryParameter;

import java.net.http.HttpResponse;

public interface PodmanSystemService {

    @GET("_ping")
    HttpResponse<Void> ping();

    @GET("events")
    HttpResponse<String> events(@QueryParameter("stream") boolean stream);

    @GET("info")
    String info();

    @GET("system/df")
    String df();

    @POST("system/prune")
    HttpResponse<Void> prune();
}
