package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        getTokens("level22.lesson13.task01", ".");
    }

    public static String[] getTokens(String query, String delimiter) {
        ArrayList<String> current = new ArrayList<>();
        String[] result;
        if (query.length() == 0){
            return (String[]) current.toArray();
        }
        else {
            StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
            while (tokenizer.hasMoreElements()){
               String token = tokenizer.nextToken();
               current.add(token);
            }
            result = new String[current.size()];
            for (int i = 0; i < current.size(); i++){
                result[i] = current.get(i);
            }
            for (String s:result) {
                System.out.println(s);
            }


        }
        return result;
    }
}
