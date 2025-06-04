package com.commons.util.adapter.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicClassCache {
    private static final Map<String, Class<?>> classCache = new ConcurrentHashMap<>();

    public static void addClassToCache(String className, Class<?> clazz) {
        classCache.putIfAbsent(className, clazz);
    }

    public static Class<?> getClassFromCache(String className) {
        return classCache.get(className);
    }

    public static boolean isClassInCache(String className) {
        return classCache.containsKey(className);
    }
}

