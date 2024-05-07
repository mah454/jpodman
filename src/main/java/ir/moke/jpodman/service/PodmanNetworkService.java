package ir.moke.jpodman.service;

import ir.moke.kafir.annotation.*;
import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.pojo.NetworkConnectContainer;
import ir.moke.jpodman.pojo.NetworkDisconnectContainer;
import ir.moke.jpodman.pojo.NetworkInfo;

import java.net.http.HttpResponse;
import java.util.List;

public interface PodmanNetworkService {

    @DELETE("networks/{name}")
    HttpResponse<Void> networkRemove(@PathParameter("name") String name, @QueryParameter("force") Boolean force);

    @POST("networks/{name}/connect")
    HttpResponse<Void> networkConnectContainer(@PathParameter("name") String name, NetworkConnectContainer networkConnectContainer);

    @POST("networks/{name}/disconnect")
    Void networkDisconnectContainer(@PathParameter("name") String name, NetworkDisconnectContainer networkDisconnectContainer);

    @GET("networks/{name}/json")
    HttpResponse<String> networkInspect(@PathParameter("name") String name);

    @POST("networks/create")
    HttpResponse<String> networkCreate(Network network);

    @GET("networks/json")
    List<NetworkInfo> networkList();
}
