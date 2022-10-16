package com.freelancing.models.dtos;

import java.util.LinkedHashMap;

/**
 * @author Alaa Jawhar
 */
public class BrowserIdMap {

    private static LinkedHashMap<String, Integer> map = new CircularLinkedMap(50);

    public static Integer get(String key) {
        synchronized (BrowserIdMap.map) {
            return BrowserIdMap.map.get(key);
        }
    }

    public static boolean isExists(String key) {
        synchronized (BrowserIdMap.map) {
            return BrowserIdMap.map.get(key) != null;
        }
    }

    public static void put(String key) {
        synchronized (BrowserIdMap.map) {
            if (BrowserIdMap.map.get(key) == null) {
                BrowserIdMap.map.put(key, 0);
            } else {
                Integer count = BrowserIdMap.map.get(key);
                BrowserIdMap.map.put(key, ++count);
            }
        }
    }

}
