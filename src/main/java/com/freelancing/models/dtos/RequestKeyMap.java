package com.freelancing.models.dtos;

import java.util.LinkedHashMap;

/**
 * @author Alaa Jawhar
 */
public class RequestKeyMap {

    private static LinkedHashMap<String, String> map = new CircularLinkedMap(100);

    public static String get(String key) {
        synchronized (RequestKeyMap.map) {
            return RequestKeyMap.map.get(key);
        }
    }

    public static boolean isExists(String key) {
        synchronized (RequestKeyMap.map) {
            return RequestKeyMap.map.get(key) != null;
        }
    }

    public static void put(String key) {
        synchronized (RequestKeyMap.map) {
            RequestKeyMap.map.put(key, key);
        }
    }

}
