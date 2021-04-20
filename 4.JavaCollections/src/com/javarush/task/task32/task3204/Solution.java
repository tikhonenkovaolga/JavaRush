package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Random random = new Random();
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String apper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String counts = "0123456789";
        char[] charLower = lower.toCharArray();
        char[] charApper = apper.toCharArray();
        char[] charCounts = counts.toCharArray();
        char[] pass = new char[8];
        for (int i = 0; i < pass.length-2; i++) {
            pass[i] = charLower[random.nextInt(charLower.length)];
            pass[i+1] = charApper[random.nextInt(charApper.length)];
            pass[i+2] = charCounts[random.nextInt(charCounts.length)];
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < pass.length; i++){
            sb = sb.append(pass[i]);
        }
        String password1 = sb.toString();
        baos.write(password1.getBytes());
        

        return baos;
    }
}