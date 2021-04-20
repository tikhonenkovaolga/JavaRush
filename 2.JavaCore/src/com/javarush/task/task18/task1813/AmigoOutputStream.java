package com.javarush.task.task18.task1813;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";

    private FileOutputStream fileOutput;

    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException {
        super("null");
        this.fileOutput = fileOutputStream;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

    @Override
    public void flush() throws IOException {
        fileOutput.flush();

    }

    @Override
    public void write(byte[] b) throws IOException {
        fileOutput.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        fileOutput.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        fileOutput.write(b);
    }

    @Override
    public void close() throws IOException {

        fileOutput.flush();
        String text = "JavaRush © All rights reserved.";
        byte[] bytes = text.getBytes();
        fileOutput.write(bytes);
        fileOutput.close();


    }
}
