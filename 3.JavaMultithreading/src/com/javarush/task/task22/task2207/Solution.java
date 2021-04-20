package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(filename);
        BufferedReader buf = new BufferedReader(fileReader);
        String line;
        while ((line = buf.readLine()) != null) {
            stringBuilder = stringBuilder.append(line).append(" ");
        }
        buf.close();
        fileReader.close();
        //System.out.println(stringBuilder.toString());
        String[] strings = stringBuilder.toString().split(" ");
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            words.add(strings[i]);
        }
//        for (int i = 0; i < words.size(); i++){
//            System.out.println(words.get(i));
//        }
        String first;
        String second;

        for (int i = 0; i < words.size(); i++) {
            first = words.get(i).trim();
            second = new StringBuilder(first).reverse().toString();
            for (int j = 1; j < words.size(); j++) {
               if (words.get(j).trim().equals(second)) {
                    Pair pair = new Pair(first, second);
                    result.add(pair);

                    words.remove(j);
                    break;

               }

            }
            words.remove(i);
            i--;
        }

        for (Pair pair : result) {
            System.out.println(pair);
        }

    }

    public static class Pair {
        String first;
        String second;

        public Pair() {

        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
