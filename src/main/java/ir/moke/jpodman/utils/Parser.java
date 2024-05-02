package ir.moke.jpodman.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    @SuppressWarnings("unchecked")
    public static Object parseIterableJson(Method method, String body) {
        ParameterizedType parameterizedType = ReflectionUtils.getMethodGenericReturnType(method);
        Class<? extends Collection<?>> collectionType = (Class<? extends Collection<?>>) parameterizedType.getRawType();
        Class<?> genericType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        return JsonUtils.toObject(body, collectionType, genericType);
    }

    @SuppressWarnings("unchecked")
    public static Object parseStringResponse(Method method, Class<?> returnType, String body) {
        if (String.class.isAssignableFrom(returnType)) {
            return String.valueOf(body);
        } else if (Void.class.isAssignableFrom(returnType)) {
            return Void.class;
        } else if (Boolean.class.isAssignableFrom(returnType) || boolean.class.isAssignableFrom(returnType)) {
            return Boolean.parseBoolean(body);
        } else if (Iterable.class.isAssignableFrom(returnType)) {
            return parseIterableJson(method, body);
        } else {
            // this block usable for pojo objects

            // 1) Generic types
            if (ReflectionUtils.isGenericType(method)) {
                ParameterizedType parameterizedType = ReflectionUtils.getMethodGenericReturnType(method);
                Class<? extends Collection<?>> rawType = (Class<? extends Collection<?>>) ReflectionUtils.getRawOfGeneric(parameterizedType);

                // a) if generic is type of Iterable
                if (Iterable.class.isAssignableFrom(rawType)) {
                    Class<?> gt = (Class<?>) ((ParameterizedType) parameterizedType.getActualTypeArguments()[0]).getActualTypeArguments()[0];
                    return JsonUtils.toObject(body, rawType, gt);

                    // b) if generic is type of Iterable
                } else if (Map.class.isAssignableFrom(rawType)) {

                    throw new RuntimeException("Not implemented yet (Map)");
                } else {
                    throw new RuntimeException("Not implemented yet (Unknown)");
                }

                // 2) pojo types
            } else {
                return JsonUtils.toObject(body, returnType);
            }
        }
    }

    public static String parsePathParameters(String apiPath, Map<String, String> parameters) {
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


    public static String parseQueryParameter(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        if (sb.isEmpty()) return "";
        return sb.substring(0, sb.length() - 1);
    }
}
