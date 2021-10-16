package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        path.toFile().deleteOnExit();

    }

    public long getFileSize(){
        long size = 0L;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public void putEntry(Entry entry) throws IOException{

        OutputStream outputStream = Files.newOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(entry);

    }

    public Entry getEntry() throws IOException, ClassNotFoundException {
        InputStream inputStream = Files.newInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        if (getFileSize() == 0){
            return null;
        }
        Entry entry = (Entry) objectInputStream.readObject();

        return entry;

    }

    public void remove() throws IOException {
        Files.delete(path);
    }


}
