package ir.moke.jpodman.utils;

import java.lang.reflect.*;
import java.util.Arrays;

public class ClassUtils {

    public static Type getReturnType(Method method) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(method.getParameters())
                    .map(Parameter::getType)
                    .toArray(Class<?>[]::new);

            return method.getDeclaringClass()
                    .getDeclaredMethod(method.getName(), parameterTypes)
                    .getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getReturnTypeClass(Method method) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(method.getParameters())
                    .map(Parameter::getType)
                    .toArray(Class<?>[]::new);

            return method.getDeclaringClass()
                    .getDeclaredMethod(method.getName(), parameterTypes)
                    .getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ParameterizedType getMethodGenericReturnType(Method method) {
        return (ParameterizedType) method.getGenericReturnType();
    }

    public static boolean isGenericType(Method method) {
        Type returnType = method.getGenericReturnType();
        return isGenericType(returnType);
    }

    public static boolean isGenericType(Type type) {
        return type instanceof ParameterizedType ||
                type instanceof TypeVariable ||
                type instanceof GenericArrayType;
    }
}
