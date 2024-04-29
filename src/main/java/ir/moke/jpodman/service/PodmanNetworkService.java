package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.NetworkConnectContainer;
import ir.moke.jpodman.pojo.Network;
import ir.moke.jpodman.pojo.NetworkDisconnectContainer;
import ir.moke.jpodman.pojo.NetworkInfo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface PodmanNetworkService {

    @DELETE
    @Path("networks/{name}")
    Response networkRemove(@PathParam("name") String name, @QueryParam("force") @DefaultValue("false") Boolean force);

    @POST
    @Path("networks/{name}/connect")
    Response networkConnectContainer(@PathParam("name") String name, NetworkConnectContainer networkConnectContainer);

    @POST
    @Path("networks/{name}/disconnect")
    Response networkDisconnectContainer(@PathParam("name") String name, NetworkDisconnectContainer networkDisconnectContainer);

    @GET
    @Path("networks/{name}/json")
    Response networkInspect(@PathParam("name") String name);

    @POST
    @Path("networks/create")
    NetworkInfo networkCreate(Network network);

    @GET
    @Path("networks/json")
    List<NetworkInfo> networkList();
}
