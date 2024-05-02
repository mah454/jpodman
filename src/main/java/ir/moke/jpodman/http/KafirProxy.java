package ir.moke.jpodman.http;

import ir.moke.jpodman.annotation.*;
import ir.moke.jpodman.utils.ClassUtils;
import ir.moke.jpodman.utils.JsonUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class KafirProxy implements InvocationHandler {
    private String baseUri;
    private final HttpClient client;

    public KafirProxy(Kafir.KafirBuilder builder) {
        baseUri = builder.getBaseUri();
        HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
        Optional.ofNullable(builder.getAuthenticator()).ifPresent(httpClientBuilder::authenticator);
        Optional.ofNullable(builder.getVersion()).ifPresent(httpClientBuilder::version);
        Optional.ofNullable(builder.getConnectionTimeout()).ifPresent(httpClientBuilder::connectTimeout);
        Optional.ofNullable(builder.getExecutorService()).ifPresent(httpClientBuilder::executor);
        Optional.ofNullable(builder.getSslContext()).ifPresent(httpClientBuilder::sslContext);

        client = httpClientBuilder.build();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, String> queryParameters = new HashMap<>();
        Map<String, String> pathParameters = new HashMap<>();
        String apiPath = "";
        Class<?> returnType = ClassUtils.getReturnTypeClass(method);
        HttpMethod methodType = HttpMethod.GET;
        if (method.isAnnotationPresent(GET.class)) {
            apiPath = method.getDeclaredAnnotation(GET.class).value();
        } else if (method.isAnnotationPresent(POST.class)) {
            apiPath = method.getDeclaredAnnotation(POST.class).value();
            methodType = HttpMethod.POST;
        } else if (method.isAnnotationPresent(PUT.class)) {
            apiPath = method.getDeclaredAnnotation(PUT.class).value();
            methodType = HttpMethod.PUT;
        } else if (method.isAnnotationPresent(DELETE.class)) {
            apiPath = method.getDeclaredAnnotation(DELETE.class).value();
            methodType = HttpMethod.DELETE;
        }

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                Parameter parameter = method.getParameters()[i];
                if (parameter.isAnnotationPresent(QueryParameter.class)) {
                    String value = parameter.getDeclaredAnnotation(QueryParameter.class).value();
                    queryParameters.put(value, String.valueOf(args[i]));
                }

                if (parameter.isAnnotationPresent(PathParameter.class)) {
                    String value = parameter.getDeclaredAnnotation(PathParameter.class).value();
                    pathParameters.put(value, String.valueOf(args[i]));
                }
            }
        }

        String formatedUri = "";
        if (queryParameters.isEmpty() && pathParameters.isEmpty()) {
            formatedUri = baseUri + apiPath;
        } else {
            formatedUri = parsePathParameters(baseUri + apiPath, pathParameters) + "?" + mapToQueryParameter(queryParameters);
        }

        URI uri = URI.create(formatedUri);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(uri);
        requestBuilder = switch (methodType) {
            case GET -> requestBuilder.GET();
            case POST -> requestBuilder.POST(initializeBodyPublisher(method, args));
            case PUT -> requestBuilder.PUT(initializeBodyPublisher(method, args));
            case DELETE -> requestBuilder.DELETE();
        };

        HttpRequest request = requestBuilder.build();

        if (returnType.isAssignableFrom(HttpResponse.class)) {
            return client.send(request, new JsonBodyHandler<>(method));
        } else {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = httpResponse.body();
            if (String.class.isAssignableFrom(returnType)) {
                return String.valueOf(body);
            } else if (Void.class.isAssignableFrom(returnType)) {
                return Void.class;
            } else if (Boolean.class.isAssignableFrom(returnType) || boolean.class.isAssignableFrom(returnType)) {
                return Boolean.parseBoolean(body);
            } else if (Iterable.class.isAssignableFrom(returnType)) {
                return parseIterableJson(method, body);
            } else {
                return JsonUtils.toObject(body, returnType);
            }
        }
    }

    private static Object parseIterableJson(Method method, String body) {
        ParameterizedType parameterizedType = ClassUtils.getMethodGenericReturnType(method);
        Class<? extends Collection<?>> collectionType = (Class<? extends Collection<?>>) parameterizedType.getRawType();
        Class<?> genericType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        return JsonUtils.toObject(body, collectionType, genericType);
    }

    private static HttpRequest.BodyPublisher initializeBodyPublisher(Method method, Object[] args) {
        Object o = extractRequestBody(method, args);
        if (o == null) return HttpRequest.BodyPublishers.noBody();
        return HttpRequest.BodyPublishers.ofString(JsonUtils.toJson(o));
    }

    private static Object extractRequestBody(Method method, Object[] args) {
        for (int i = 0; i < method.getParameters().length; i++) {
            if (method.getParameters()[i].getDeclaredAnnotations().length == 0) {
                return args[i];
            }
        }

        return null;
    }

    private String mapToQueryParameter(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        if (sb.isEmpty()) return "";
        return sb.substring(0, sb.length() - 1);
    }

    private String parsePathParameters(String apiPath, Map<String, String> parameters) {
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
