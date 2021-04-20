package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        AnyObject anyObject = null;
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        try {
            anyObject = softReference.get();
        } catch (NullPointerException e) {

        }

        return anyObject;
    }

    public AnyObject put(Long key, AnyObject value) {
        AnyObject anyObject = null;
        for (Map.Entry<Long, SoftReference<AnyObject>> entry : cacheMap.entrySet()) {
            if (entry.getKey() == key & entry.getValue() != null) {
                anyObject = entry.getValue().get();
            }


        }
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        if (softReference != null) {
            softReference.clear();
        }

        return anyObject;
    }

    public AnyObject remove(Long key) {
        AnyObject anyObject = null;
        for (Map.Entry<Long, SoftReference<AnyObject>> entry : cacheMap.entrySet()) {
            if (entry.getKey() == key & entry.getValue() != null) {
                anyObject = entry.getValue().get();
            }

        }
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        if (softReference != null) {
            softReference.clear();
        }
        return anyObject;
    }
}