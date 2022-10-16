package com.freelancing.models.dtos;

import java.util.LinkedHashMap;

/**
 * @author Alaa Jawhar
 */
public class EmailMap {

    private static LinkedHashMap<String, Integer> map = new CircularLinkedMap(100);

    public static Integer get(String key) {
        synchronized (EmailMap.map) {
            return EmailMap.map.get(key);
        }
    }

    public static boolean isExists(String key) {
        synchronized (EmailMap.map) {
            return EmailMap.map.get(key) != null;
        }
    }

    public static void put(String key) {
        synchronized (EmailMap.map) {
            if (EmailMap.map.get(key) == null) {
                EmailMap.map.put(key, 0);
            } else {
                Integer count = EmailMap.map.get(key);
                EmailMap.map.put(key, ++count);
            }
        }
    }

}
