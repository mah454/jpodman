import ir.moke.jpodman.pojo.ContainerInfo;
import ir.moke.kafir.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws ClassNotFoundException {
        final String json = """
                [
                  {
                    "AutoRemove": false,
                    "Command": [
                      "/bin/loop"
                    ],
                    "Created": "2024-05-08T10:45:52.029156128+03:30",
                    "CreatedAt": "",
                    "CIDFile": "",
                    "Exited": false,
                    "ExitedAt": -62135596800,
                    "ExitCode": 0,
                    "Id": "257fdd29cfbd2aa5b08be9b4551810cb9003e118d4a61769d24263e8e8c405f9",
                    "Image": "localhost/sample:latest",
                    "ImageID": "39a0cd85794675007d5c48f7087c996f6c4cad48935f2b846f91f44bc3546d6b",
                    "IsInfra": false,
                    "Labels": {
                      "io.buildah.version": "1.33.5"
                    },
                    "Mounts": [
                      "/sample",
                      "/aaa"
                    ],
                    "Names": [
                      "c1"
                    ],
                    "Namespaces": {},
                    "Networks": [
                      "sample"
                    ],
                    "Pid": 0,
                    "Pod": "",
                    "PodName": "",
                    "Ports": null,
                    "Restarts": 0,
                    "Size": null,
                    "StartedAt": 1715152569,
                    "State": "created",
                    "Status": ""
                  }
                ]
                """;
        List<ContainerInfo> containerInfoList = new ArrayList<>();
        JsonUtils.toObject(json,List.class,ContainerInfo.class);
    }
}
