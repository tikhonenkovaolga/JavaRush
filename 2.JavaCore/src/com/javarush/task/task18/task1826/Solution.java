package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        switch (args[0]){
            case ("-e"):
                FileInputStream file = new FileInputStream(args[1]);
                byte[] bytes = new byte[file.available()];

                while (file.available() > 0){
                    file.read(bytes);
                }
                file.close();
                for (int i = 0; i < bytes.length; i++){
                           bytes[i] = (byte) (bytes[i] + 1);

                }

                FileOutputStream result = new FileOutputStream(args[2]);
                result.write(bytes);
                result.close();
                break;

            case ("-d"):
                FileInputStream file1 = new FileInputStream(args[1]);
                byte[] bytes1 = new byte[file1.available()];
                while (file1.available() > 0){
                    file1.read(bytes1);
                }
                file1.close();
                for (int i = 0; i < bytes1.length; i++){
                         bytes1[i] = (byte) (bytes1[i]- 1);

                }
                FileOutputStream result1 = new FileOutputStream(args[2]);
                result1.write(bytes1);
                result1.close();
                break;
        }

    }

}
