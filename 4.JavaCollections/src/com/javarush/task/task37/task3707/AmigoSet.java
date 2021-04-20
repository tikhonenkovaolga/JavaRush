package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {

//    public static class Solution {
//        public static void main(String[] args) throws IOException, ClassNotFoundException {
//            HashSet<String> hashSet = new HashSet<>();
//            hashSet.add("ddd");
//            hashSet.add("rrrr");
//            AmigoSet amigoSet = new AmigoSet(hashSet);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream =
//                    new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(amigoSet);
//            objectOutputStream.close();
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            AmigoSet amigoSet1 = (AmigoSet)  objectInputStream.readObject();
//            System.out.println(amigoSet.equals(amigoSet1));
//            System.out.println(amigoSet);
//            System.out.println("________");
//            System.out.println(amigoSet1);
//        }
//    }

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<E, Object>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = (int) Math.max(16, Math.ceil(collection.size() / .75f));
        map = new HashMap<E, Object>(capacity);
        addAll(collection);
    }

    @Override
    public Iterator iterator() {
        Set<E> setKey = map.keySet();
        return setKey.iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        boolean newElement = true;
        for (Map.Entry<E, Object> entry : map.entrySet()) {
            if (entry.getKey().equals(e)) {
                newElement = false;
            }

        }
        if (newElement) {
            map.put(e, PRESENT);
        }
        return newElement;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    public Object clone() {
        AmigoSet<E> cloneAmigo = null;
        try {
            cloneAmigo = (AmigoSet<E>) super.clone();
            cloneAmigo.map = (HashMap<E, Object>) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return cloneAmigo;
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        int size = map.size();
        outputStream.writeInt(size);
        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        outputStream.writeInt(capacity);
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        outputStream.writeFloat(loadFactor);

        for (Map.Entry<E, Object> entry : map.entrySet()){
            outputStream.writeObject(entry.getKey());
        }


    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        int size = inputStream.readInt();
        map = new HashMap<>(inputStream.readInt(), inputStream.readFloat());
        for (int i = 0; i < size; i++){
            E key = (E) inputStream.readObject();
            map.put(key, PRESENT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmigoSet)) return false;
        if (!super.equals(o)) return false;
        AmigoSet<?> amigoSet = (AmigoSet<?>) o;
        return Objects.equals(map, amigoSet.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map);
    }


}


