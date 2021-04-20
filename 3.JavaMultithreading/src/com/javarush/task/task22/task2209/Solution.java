package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.shuffle;

/*
Составить цепочку слов
*/
public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));
        String fileName = reader.readLine();
        reader.close();
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader buf = new BufferedReader(fileReader);
        String line;
        while ((line = buf.readLine()) != null) {
            stringBuilder.append(line).append(" ");
        }
        buf.close();
        fileReader.close();
        String[] strings = stringBuilder.toString().split(" ");
        StringBuilder result = getLine(strings);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        String[] wordsClone = words.clone();
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        if (words.length == 0) {
            return result1;
        }
        ArrayList<String> word = new ArrayList<>();
        Collections.addAll(word, wordsClone);
        Collections.sort(word);
        result1.append(word.get(0));

        word.remove(0);

        while (!word.isEmpty()) {
            String next = word.get(0);
            result2 = findNextWord(next, result1);
            if (result2.toString().length() > result1.toString().length()) {
                word.remove(0);
                result1 = result2;
            } else {
                String curr = word.get(0);
                word.add(word.size(), curr);
                word.remove(0);

            }
        }



        return result2;

    }



    private static StringBuilder findNextWord(String checkWord, StringBuilder readySb) {
        StringBuilder returnBuilder = new StringBuilder();
        String startSymbol = checkWord.substring(0, 1);
        String endSymbol = checkWord.substring(checkWord.length() - 1);

        if (readySb.toString().toLowerCase().startsWith(endSymbol.toLowerCase())) {
            StringBuilder tmp1 = new StringBuilder(checkWord);
            returnBuilder = tmp1.append(" ").append(readySb.toString());

            return returnBuilder;
        } else if (readySb.toString().toLowerCase().endsWith(startSymbol.toLowerCase())) {
            StringBuilder tmp2 = new StringBuilder(readySb.toString());
            returnBuilder = tmp2.append(" ").append(checkWord);
            return returnBuilder;
        }


        return returnBuilder;
    }


}



