package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public FileStorageStrategy() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        if (key == null) return Boolean.parseBoolean(null);
        boolean result = false;
        try {
            if (getEntry(key) != null) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null) return false;
        boolean result = false;
        FileBucket[] tab = table;
        for (int i = 0; i < tab.length; i++) {
            try {
                for (Entry e = tab[i].getEntry(); e != null; e = e.next) {
                    if (value.equals(e.value))
                        result = true;
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e1) {

            }


        }

        return result;
    }

    @Override
    public void put(Long key, String value) {
        FileBucket[] tab = table;
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        try {
            if (this.containsKey(key)) {
                for (int i = 0; i < tab.length; i++) {
                    for (Entry e = tab[i].getEntry(); e != null; e = e.next) {
                        long k;
                        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                            e.value = value;

                        }
                    }
                }

            } else addEntry(hash, key, value, index);
        } catch (IOException e) {

        } catch (ClassNotFoundException e1) {

        }
    }

    @Override
    public Long getKey(String value) {
        Long result = 0L;

        if (this.containsValue(value)) {
            FileBucket[] tab = table;
            for (int i = 0; i < tab.length; i++) {
                try {
                    for (Entry e = tab[i].getEntry(); e != null; e = e.next) {
                        if (value.equals(e.value))
                            result = e.key;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    public String getValue(Long key) {
        String result = "";
        try {
            result = getEntry(key).getValue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) throws IOException, ClassNotFoundException {
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }

        return null;
    }

    public void resize(int newCapacity) throws ClassNotFoundException, IOException {

        FileBucket[] newTable = new FileBucket[newCapacity];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new FileBucket();
        }
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) throws ClassNotFoundException, IOException {
        int newCapacity = newTable.length;
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();

            while (entry != null) {
                Entry next = entry.next;
                int indexFor = indexFor(entry.hash, newCapacity);
                entry.next = newTable[indexFor].getEntry();
                newTable[indexFor].putEntry(entry);
                entry = next;
            }
            table[i].remove();
            table[i] = null;
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) throws IOException, ClassNotFoundException {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * DEFAULT_INITIAL_CAPACITY);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) throws ClassNotFoundException, IOException {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));

    }


}
