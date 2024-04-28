package ir.moke.jpodman.utils;

import java.util.List;

public class StringUtils {
    public static String arrayToCommaDelimited(List<String> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(item -> sb.append(item).append(","));
        return sb.substring(0,sb.lastIndexOf(","));
    }
}
