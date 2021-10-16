package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {

        if (first == null & second == null || first.equals("") & second.equals("")) return true;
        if (first.equals(second)) return true;
        if (first.length() - second.length() > 1 || second.length() - first.length() > 1) return false;
        if ((first.length() == 0 & second.length() == 1) || (first.length() == 1 & second.length() == 0)) return true;
        boolean result = false;
        int count = 0;
        char[] charsFirst = first.toCharArray();
        char[] charsSecond = second.toCharArray();
        ArrayList<Character> f = new ArrayList<>();
        ArrayList<Character> s = new ArrayList<>();
        for (Character c : charsFirst) {
            f.add(c);
        }
        for (Character c : charsSecond) {
            s.add(c);
        }
        if (first.length() == second.length()) {
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i) != s.get(i)) count++;
            }
            if (count < 2) result = true;
        } else {
            if (f.size() < s.size()) {
                for (int i = 0; i < s.size(); i++) {
                    if (f.get(i) != s.get(i)) {
                        count++;
                        s.remove(s.get(i));
                        i--;
                    } else {
                        s.remove(s.get(i));
                        f.remove(f.get(i));
                        i--;
                        if (f.isEmpty() & s.size() == 1) {
                            return true;
                        }
                    }
                }
                if (count < 2) result = true;
            } else if (s.size() < f.size()) {
                for (int i = 0; i < f.size(); i++) {
                    if (f.get(i) != s.get(i)) {
                        count++;
                        f.remove(f.get(i));
                        i--;
                    } else {
                        s.remove(s.get(i));
                        f.remove(f.get(i));
                        i--;
                        if (s.isEmpty() & f.size() == 1) {
                            return true;
                        }
                    }

                }
                if (count < 2) result = true;
            }

        }
        return result;
    }
}

