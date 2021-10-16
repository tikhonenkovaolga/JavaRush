package com.javarush.task.task36.task3605;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        FileInputStream fis = new FileInputStream(fileName);
        int i;
        ArrayList<Character> symbols = new ArrayList<>();
        while ((i = fis.read()) != -1){
            symbols.add((char) i);
        }
        fis.close();

        for (int t = 0; t < symbols.size()-1; t++){
            for (int s = t+1; s < symbols.size(); s++){
                if (symbols.get(t) == symbols.get(s)){
                    symbols.remove(symbols.get(s));
                    }
            }
        }

        StringBuilder text = new StringBuilder();
        for (Character c : symbols) {
            text.append(c);
        }
        String textFile = text.toString().toLowerCase();
        Pattern pattern1 = Pattern.compile("[\\s\\d.,\\-\\n\\/\\\\]+");
        Matcher matcher = pattern1.matcher(textFile);
        String result = matcher.replaceAll("");
        char[] letters = result.toCharArray();

        TreeSet<Character> treeSet = new TreeSet<>();
        for (int j = 0; j < letters.length; j++){
            treeSet.add(letters[j]);
        }

        treeSet.stream().limit(5).forEach(System.out::print);// обрезать три сет до 5 объектов



    }
}
