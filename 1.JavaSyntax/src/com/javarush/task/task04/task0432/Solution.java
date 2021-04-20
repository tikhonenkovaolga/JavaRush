package com.javarush.task.task04.task0432;



/* 
Хорошего много не бывает
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stroka = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        int i = 1;
        while (i <= n){
            System.out.println(stroka);
            i++;
        }


    }
}
