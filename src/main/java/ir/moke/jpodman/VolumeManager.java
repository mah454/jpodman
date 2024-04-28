package ir.moke.jpodman;

import ir.moke.jpodman.pojo.Volume;
import ir.moke.jpodman.utils.CommandExecutor;
import ir.moke.jpodman.utils.JsonUtils;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class VolumeManager {

    public static Volume[] inspect(String name) {
        String result = CommandExecutor.execute("podman", "volume", "inspect", name == null ? "--all" : name);
        return JsonUtils.toObject(result, Volume[].class);
    }

    public static String create(String name, String driver, Map<String, String> labels, Map<String, String> options) {
        StringBuilder sb = new StringBuilder("podman volume create ");
        Optional.ofNullable(name).ifPresent(item -> sb.append(item).append(" "));
        Optional.ofNullable(driver).ifPresent(item -> sb.append("--driver ").append(item));
        labels.forEach((k, v) -> sb.append("--label ").append(k).append("=").append(v).append(" "));
        options.forEach((k, v) -> sb.append("--opt ").append(k).append("=").append(v).append(" "));

        return CommandExecutor.execute(sb.toString().split(" "));
    }

    public static String create(String name) {
        return create(name, null, Collections.emptyMap(), Collections.emptyMap());
    }

    public static String create(String name, String driver) {
        return create(name, driver, Collections.emptyMap(), Collections.emptyMap());
    }

    public static String create(String name, String driver, Map<String, String> labels) {
        return create(name, driver, labels, Collections.emptyMap());
    }

    public static void remove(String name) {
        CommandExecutor.execute("podman", "volume", "rm", name);
    }

    public static void prune() {
        CommandExecutor.execute("podman", "volume", "prune", "--force");
    }

    public static InputStream export(String name) {
        return CommandExecutor.executeToStream("podman", "volume", "export", name);
    }
}
