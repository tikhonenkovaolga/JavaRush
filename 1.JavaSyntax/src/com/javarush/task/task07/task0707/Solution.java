package com.javarush.task.task07.task0707;

import java.util.ArrayList;

/* 
Что за список такой?
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList<String >();

        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");
        list.add(3, "d");
        list.add(4, "e");

        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++){
            //int j = list.size() - i - 1;
            System.out.println( list.get(i) );
        }

    }
}
