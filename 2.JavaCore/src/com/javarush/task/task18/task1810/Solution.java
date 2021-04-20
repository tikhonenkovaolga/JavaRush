package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileInputStream file = new FileInputStream(reader.readLine());
            byte[] buff = new byte[file.available()];
            while (file.available() > 0){
                int count = file.read(buff);
                if (count < 1000){
                    file.close();
                    throw new DownloadException();
                }
                else {
                    file = new FileInputStream(reader.readLine());
                }
            }
        file.close();
        }
        catch (IOException e){

        }



    }

    public static class DownloadException extends Exception {

    }
}
