package com.javarush.task.task08.task0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Минимальное из N чисел
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        List<Integer> integerList = getIntegerList();
        System.out.println(getMinimum(integerList));
    }

    public static int getMinimum(List<Integer> array) {
       return  Collections.min(array);
    }

    public static List<Integer> getIntegerList() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            list.add(sc.nextInt());
        }
        return list;
    }
}
