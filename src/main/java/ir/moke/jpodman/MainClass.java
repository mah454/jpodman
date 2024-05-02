package ir.moke.jpodman;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    private static final String baseURL = "http://%s:%s/v5/libpod/";
    private static final String host = "127.0.0.1";
    private static final int port = 9000;

    public static void main(String[] args) {
        String path = "/images/";
        Map<String, String> param = Map.of("name", "ali","age","12");

        String formatted = parsePathParameters(path, param);
        System.out.println(formatted);
    }

    private static String parsePathParameters(String apiPath, Map<String, String> parameters) {
        final Pattern pattern = Pattern.compile("\\{.*?\\}");
        Matcher matcher = pattern.matcher(apiPath);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String str = matcher.group();
            String replacement = parameters.get(str.replace("{", "").replace("}", ""));
            matcher.appendReplacement(sb, replacement);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
