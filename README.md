## Communicate with podman rest api service

### Implementations

* PodmanContainerService
* PodmanImageService
* PodmanNetworkService
* PodmanPodService
* PodmanSystemService
* PodmanVolumeService

### Usage:

Add dependency to pom.xml 
```xml
<dependency>
    <groupId>ir.moke</groupId>
    <artifactId>jpodman</artifactId>
    <version>1.0</version>
</dependency>
```

Example Code : 
```java
public class MainClass {
    public static void main(String[] args) {
        try (Podman podman = new Podman()) {
            PodmanSystemService podmanSystemService = podman.api(PodmanSystemService.class);

            try (Response response = podmanSystemService.info()) {
                System.out.println(response.getStatus());
                System.out.println(response.readEntity(String.class));
            }
        }
    }
}
```
