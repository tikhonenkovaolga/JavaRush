package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();   //TODO add your code here


    public V getByKey(K key, Class<V> clazz) throws Exception {
        V newV = cache.get(key);
        if (newV == null) {

            newV = (V) clazz.getConstructor(key.getClass()).newInstance(key);
            put(newV);

        }
        return newV;
    }

    public boolean put(V obj) {
        boolean result = false;
        int sizeCash = size();
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);

            cache.put((K) method.invoke(obj), obj);
        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {
            // e.printStackTrace();
        } catch (InvocationTargetException e) {
            // e.printStackTrace();
        }
        if (sizeCash < size()) {
            result = true;
        }

        return result;
    }

    public int size() {
        return cache.size();
    }
}
