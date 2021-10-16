package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        boolean result = false;
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++){
            for (Entry e = tab[i]; e != null; e = e.next){
                if (value.equals( e.value ))
                    result = true;
            }

        }

        return result;
    }

    @Override
    public void put(Long key, String value) {
        Entry[] tab = table;
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (this.containsKey(key)){
            for (int i = 0; i < tab.length; i++) {
                for (Entry e = tab[i]; e != null; e = e.next) {
                    Object k;
                    if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                        e.value = value;

                    }
                }
            }

        }
        else addEntry(hash,key, value, index);
    }

    @Override
    public Long getKey(String value) {
        Long result = 0L;
        if (this.containsValue(value)){
            Entry[] tab = table;
            for (int i = 0; i < tab.length; i++) {
                for (Entry e = tab[i]; e != null; e = e.next) {
                    if (value.equals(e.value))
                        result = e.key;
                }
            }
        }

        return result;
    }

    @Override
    public String getValue(Long key) {
        String result = "";
        if (this.containsKey(key)){
            int hash = hash(key);
            Entry[] tab = table;
            for (int i = 0; i < tab.length; i++) {
                for (Entry e = tab[i]; e != null; e = e.next) {
                    if (hash(e.key)== hash && key.equals(e.key))
                        result = e.value;
                }
            }
        }

        return result;
    }

    public int hash(Long k) {
        return (k == null) ? 0 : (int) ((k.hashCode()) ^ (k >>> 16));
    }

    public int indexFor(int hash, int length){
        return hash & (length-1);
    }

    public Entry getEntry(Long key){
        Entry entry = null;
        Entry[] tab = table;
        int hash = (key == null) ? 0 : hash(key);
        for (int i = 0; i < tab.length; i++) {
            for (Entry e = tab[i]; e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    entry = e;
            }
        }
        return entry;
    }

    public void resize(int newCapacity){
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == DEFAULT_INITIAL_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);



    }

    public void transfer(Entry[] newTable){
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }


}
