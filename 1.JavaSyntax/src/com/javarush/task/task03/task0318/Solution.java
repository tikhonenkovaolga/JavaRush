package com.javarush.task.task03.task0318;

/* 
План по захвату мира
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String scount = reader.readLine();
        int count = Integer.parseInt(scount);
        String name = reader.readLine();




        System.out.println(name + " захватит мир через " + count + " лет. Му-ха-ха!");


    }
}
