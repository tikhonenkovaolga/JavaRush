package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.add(abcArray[i]);
        }
        // Ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }
        String str = list.toString();
        //System.out.println(str);

        char[] charInput = str.toCharArray();
        ArrayList<Character> strChar = new ArrayList<>();
        for (int i = 0; i < charInput.length; i++) {
            strChar.add(charInput[i]);
        }

        int count = 0;
        for (int i = 0; i < alphabet.size(); i++) {
            for (int j = 0; j < strChar.size(); j++) {
                if (alphabet.get(i).equals(strChar.get(j))) {
                      count++;
                    }
                }
                System.out.println(alphabet.get(i) + " " + count);
                count = 0;
            }

        }


    }




