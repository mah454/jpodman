## Communicate with podman rest api service 

### Implementations
* PodmanContainerService
* PodmanImageService
* PodmanNetworkService
* PodmanPodService
* PodmanSystemService
* PodmanVolumeService

### Usage:   
```java
public class MainClass {
    public static void main(String[] args) {
        try (Podman podman = new Podman()){
            PodmanSystemService podmanSystemService = podman.api(PodmanSystemService.class);
            
            try (Response response = podmanSystemService.info()){
                System.out.println(response.getStatus());
                System.out.println(response.readEntity(String.class));
            }
        }
    }
}
```
