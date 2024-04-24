package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class VolumeTest {

    @Test
    public void createVolume() {
        Map<String, String> labels = new HashMap<>();
        labels.put("name", "a1");
        labels.put("age", "22");
        String volumeId = VolumeManager.create("sample2", null, labels);
        System.out.println(volumeId);
    }

    @Test
    public void getAllVolumes() {
        Volume[] inspect = VolumeManager.inspect(null);
        for (Volume volume : inspect) {
            System.out.println(volume.toYaml());

            System.out.println("------------------------------");
        }
    }

    @Test
    public void exportVolume() {
        try (InputStream is = VolumeManager.export("postgresql")){
            FileOutputStream fos = new FileOutputStream("/tmp/sample.tar");
            fos.write(is.readAllBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
