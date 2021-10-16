package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int count = 0;
        Collection<List<V>> collection = map.values();
        for (List<V> l : collection) {
            count += l.size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {

        V v = null;
        if (!map.containsKey(key)) {
            ArrayList<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return v;
        } else {
            for (Entry<K, List<V>> entry : map.entrySet()) {
                if (entry.getKey().equals(key)) {
                    ArrayList<V> entryValue = (ArrayList<V>) entry.getValue();
                    v = entryValue.get(entryValue.size() - 1);

                    if (entryValue.size() < repeatCount) {
                        entryValue.add(value);
                        map.put(key, entryValue);
                    } else if (entryValue.size() == repeatCount) {
                        entryValue.remove(entryValue.get(0));
                        entryValue.add(value);
                        map.put(key, entryValue);

                    }
                }

            }
        }


        return v;
    }

    @Override
    public V remove(Object key) {
        V v = null;

        if (map.containsKey(key)) {
            ArrayList<V> list = (ArrayList<V>) map.get(key);
            if (list.isEmpty()) {
                map.remove(key, list);
            } else {
                v = list.get(0);
                list.remove(list.get(0));
                if (list.isEmpty()) map.remove(key, list);

            }

        }


        return v;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()){
            for (V v : entry.getValue()){
                list.add(v);
            }
        }

        return list;

    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        boolean contains = false;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getValue().contains(value)) {
                contains = true;
            }
        }
        return contains;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}