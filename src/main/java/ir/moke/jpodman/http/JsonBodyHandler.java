package ir.moke.jpodman.http;

import ir.moke.jpodman.utils.ReflectionUtils;
import ir.moke.jpodman.utils.JsonUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {
    private Class<T> responseType;
    private Class<? extends Collection<T>> collectionType;
    private Class<? extends Map<String, T>> mapType;
    private Class<T> objectType;

    public JsonBodyHandler(Method method) {
        detectReturnType(method);
    }

    @Override
    @SuppressWarnings("unchecked")
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {

        if (responseType != null && String.class.isAssignableFrom(responseType)) {
            return (HttpResponse.BodySubscriber<T>) HttpResponse.BodySubscribers.ofString(Charset.defaultCharset());
        } else {
            HttpResponse.BodySubscriber<String> subscriber = HttpResponse.BodySubscribers.ofString(Charset.defaultCharset());
            return HttpResponse.BodySubscribers.mapping(subscriber, this::getResult);
        }
    }

    private T getResult(String s) {
        if (s == null || s.isEmpty()) return null;
        if (!JsonUtils.isJson(s)) return (T) s;
        if (collectionType != null) {
            return JsonUtils.toObject(s, collectionType, objectType);
        } else {
            return JsonUtils.toObject(s, responseType);
        }
    }

    @SuppressWarnings("unchecked")
    private void detectReturnType(Method method) {
        try {
            ParameterizedType genericReturnType = ReflectionUtils.getMethodGenericReturnType(method);
            if (ReflectionUtils.isGenericType(genericReturnType.getActualTypeArguments()[0])) {
                ParameterizedType actualTypeArgument = (ParameterizedType) genericReturnType.getActualTypeArguments()[0];
                if (isCollection(actualTypeArgument)) {
                    collectionType = (Class<? extends Collection<T>>) actualTypeArgument.getRawType();
                    Type collectionGenericType = actualTypeArgument.getActualTypeArguments()[0];

                    if (ReflectionUtils.isGenericType(collectionGenericType)) {
                        /*
                         * Json key always is string
                         * */
                        throw new RuntimeException("List<Map<String,T>> does not supported yet");
                    } else {
                        objectType = (Class<T>) actualTypeArgument.getActualTypeArguments()[0];
                    }
                } else if (isMap(actualTypeArgument)) {
                    ParameterizedType t = (ParameterizedType) actualTypeArgument.getActualTypeArguments()[0];
                    mapType = (Class<? extends Map<String, T>>) t.getRawType();
                } else {
                    objectType = (Class<T>) actualTypeArgument.getActualTypeArguments()[0];
                }
            } else {
                responseType = (Class<T>) genericReturnType.getActualTypeArguments()[0];
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isCollection(ParameterizedType actualTypeArgument) {
        return Collection.class.isAssignableFrom((Class<?>) actualTypeArgument.getRawType());
    }

    private static boolean isMap(ParameterizedType parameterizedType) {
//        return Map.class.isAssignableFrom((Class<?>) ((ParameterizedType) actualTypeArgument.getActualTypeArguments()[0]).getRawType());
        return Map.class.isAssignableFrom((Class<?>) parameterizedType.getRawType());
    }
}
