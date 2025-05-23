package ir.moke.jpodman.service;

import ir.moke.jpodman.pojo.ExecID;
import ir.moke.jpodman.pojo.ExecInstance;
import ir.moke.kafir.annotation.GET;
import ir.moke.kafir.annotation.POST;
import ir.moke.kafir.annotation.PathParameter;
import ir.moke.kafir.annotation.QueryParameter;

import java.net.http.HttpResponse;

public interface PodmanExecService {

    @POST("containers/{name}/exec")
    HttpResponse<ExecID> createExecInstance(@PathParameter("name") String name, ExecInstance instance);

    @GET("exec/{id}/json")
    HttpResponse<String> inspectExecInstance(@PathParameter("id") String id);

    @POST("exec/{id}/resize")
    HttpResponse<Void> resizeExecInstance(@PathParameter("id") String id,
                                          @QueryParameter("h") int height,
                                          @QueryParameter("w") int width);
}
