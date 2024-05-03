package ir.moke.jpodman.service;

import ir.moke.kafir.annotation.GET;
import ir.moke.kafir.annotation.POST;
import ir.moke.kafir.annotation.QueryParameter;

import java.net.http.HttpResponse;

public interface PodmanSystemService {

    @GET("_ping")
    HttpResponse<String> ping();

    @GET("events")
    HttpResponse<String> events(@QueryParameter("stream") boolean stream);

    @GET("info")
    String info();

    @GET("system/df")
    String df();

    @POST("system/prune")
    HttpResponse<Void> prune();
}
