package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        s = s.replaceAll("\\s+", "");
        char [] chars = s.toCharArray();
        ArrayList<Character> listVowels = new ArrayList<>();
        ArrayList<Character> listAnVowels = new ArrayList<>();
        for (int i = 0; i < chars.length; i++){
            if (isVowel(chars[i]) == true){
                listVowels.add(chars[i]);
            }
            else {
                listAnVowels.add(chars[i]);
            }
        }
        for (int i = 0; i < listVowels.size(); i++){
            System.out.print(listVowels.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < listAnVowels.size(); i++){
            System.out.print(listAnVowels.get(i) + " ");
        }

    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   // ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}