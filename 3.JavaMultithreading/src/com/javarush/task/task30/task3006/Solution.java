package com.javarush.task.task30.task3006;

/* 
Swap по-новому
*/

public class Solution {
    public static void main(String[] args) {
        /* expected output
        x=4, y=5
        x=5, y=4
         */

        Pair pair = new Pair(4, 5);
        System.out.println(pair);
        pair.swap();
        System.out.println(pair);
    }
}

//Первое выражение определяет исключающую маску - общее значение, состоящее из уникальных (неповторяемых) битов.
//
//1. a = 0000 1010 ^ 0000 1001 = 0000 0011
//
//Второе выражение маскирует биты b, позволяя выделить биты a.
//
//2. b = 0000 1001 ^ 0000 0011 = 0000 1010
//
//Третье выражение маскирует биты a, позволяя выделить биты b.
//
//3. a = 0000 0011 ^ 0000 1010 = 0000 1001