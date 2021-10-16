package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {

    private StorageStrategy storageStrategy;

    private Long id;
    private String s;
    private Long lastId = 0L;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        long idResult = 0L;
        if (storageStrategy.containsValue(string)) {
            idResult = storageStrategy.getKey(string);
        } else {
            lastId += 1;
            storageStrategy.put(lastId, string);
            idResult = lastId;
        }
        return idResult;
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
       
    }
}
