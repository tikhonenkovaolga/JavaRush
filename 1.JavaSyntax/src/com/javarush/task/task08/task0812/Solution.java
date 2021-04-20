package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(i, Integer.parseInt(reader.readLine()));
        }
        int count = 1;
        int max = 1;
        for (int i = 0; i < list.size()-1; i++){
            if (list.get(i).equals(list.get(i + 1))) {
                count++;
                if (max < count){
                    max = count;
                }
            }
            else
                count = 1;

        }
        System.out.println(max);

    }
}

//Требования:
//        1. Программа должна выводить число на экран.
//        2. Программа должна считывать значения с клавиатуры.
//        3. В методе main объяви переменную типа ArrayList с типом элементов Integer и сразу проинициализируй ee.
//        4. Программа должна добавлять в коллекцию 10 чисел, согласно условию.
  //      5. Программа должна выводить на экран длину самой длинной последовательности повторяющихся чисел в списке.