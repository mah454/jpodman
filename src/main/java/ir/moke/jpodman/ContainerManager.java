package ir.moke.jpodman;

import ir.moke.jpodman.pojo.RestartPolicy;
import ir.moke.jpodman.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContainerManager {

    public static void run(String name,
                           String hostname,
                           String dns,
                           String searchDomain,
                           Boolean privileged,
                           Boolean removeAfterExist,
                           RestartPolicy restartPolicy,
                           String tz,
                           List<String> networks,
                           List<String> exposePorts,
                           Map<String, String> publishPorts,
                           Boolean publishAll,
                           Map<String, String> hosts,
                           Map<String, String> environments,
                           Map<String, String> volumes,
                           String imageName) {
        StringBuilder sb = new StringBuilder("podman run -d ");
        Optional.ofNullable(name).ifPresent(item -> sb.append("--name=").append(item).append(" "));
        Optional.ofNullable(hostname).ifPresent(item -> sb.append("--hostname=").append(item).append(" "));
        Optional.ofNullable(dns).ifPresent(item -> sb.append("--dns=").append(item).append(" "));
        Optional.ofNullable(searchDomain).ifPresent(item -> sb.append("--dns-search=").append(item).append(" "));
        Optional.ofNullable(privileged).ifPresent(item -> sb.append("--privileged").append(" "));
        Optional.ofNullable(removeAfterExist).ifPresent(item -> sb.append("--rm").append(" "));
        Optional.ofNullable(restartPolicy).ifPresent(item -> sb.append("--restart=").append(item.getValue()).append(" "));
        Optional.ofNullable(tz).ifPresent(item -> sb.append("--tz=").append(item).append(" "));
        Optional.ofNullable(networks).ifPresent(item -> sb.append("--networks=").append(StringUtils.arrayToCommaDelimited(networks)).append(" "));
        Optional.ofNullable(exposePorts).ifPresent(item -> sb.append("--expose=").append(StringUtils.arrayToCommaDelimited(item)).append(" "));
        publishPorts.forEach((k, v) -> sb.append("--publish=").append(k).append(":").append(v).append(" "));
        Optional.ofNullable(publishAll).ifPresent(item -> sb.append("--publish-all").append(" "));
        hosts.forEach((k, v) -> sb.append("--add-host=").append(k).append(":").append(v).append(" "));
        environments.forEach((k, v) -> sb.append("--env=").append(k).append("=").append(v).append(" "));
        volumes.forEach((k, v) -> sb.append("--volume=").append(k).append(":").append(v).append(" "));

        sb.append(imageName);

        System.out.println(sb);
    }
}
