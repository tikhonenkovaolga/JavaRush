package com.javarush.task.task18.task1814;

import java.io.*;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {

    private static String fileName;
    //private static FileInputStream fileInput;
    public TxtInputStream(String fileName) throws FileNotFoundException, UnsupportedFileNameException, IOException{
        super(fileName);

        if (!fileName.endsWith(".txt")){
            super.close();
            throw new UnsupportedFileNameException();



        }

    }



    public static void main(String[] args) throws FileNotFoundException, UnsupportedFileNameException, IOException{
      //  new TxtInputStream(fileName);

    }
}

