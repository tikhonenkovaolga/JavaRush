package com.javarush.task.task06.task0611;

/* 
Класс StringHelper
*/

public class StringHelper {
    public static String multiply(String s) {
        String result = "";
       for (int i = 0; i < 5; i++){
           result = result + s;
           //System.out.print(result);

       }
        return result;

        }

    public static String multiply(String s, int count) {
        //count = 4;
        String result = "";
        for (int i = 0; i < count; i++){
            result = result + s;
         //   System.out.print(result);
        }
        return result;
    }

    public static void main(String[] args) {
        //multiply("Амиго");
      //  multiply("Амиго", 4);

    }
}
