package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис jjjjjjjj mmmmm."));
    }

    public static String getPartOfString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        if (string == null || string.split(" ").length < 5){
           throw new TooShortStringException();
        }
        else{
            for (int i = 1; i < 5  ; i++){
                stringBuilder.append(string.split(" ")[i]);
                if (i < 4)stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public static class TooShortStringException extends RuntimeException {

    }
}
