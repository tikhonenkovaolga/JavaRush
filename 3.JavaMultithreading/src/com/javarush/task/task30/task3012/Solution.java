package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(2019);


    }

    public void createExpression(int number) {

        StringBuilder result = new StringBuilder(number + " = ");

        for (int i = 0; i < 8; i++) {

            int count = number / 3;
            if (count < 0) {
                break;
            }
            number = number % 3;

            if (number == 1) {
                result.append(" + ").append((int) Math.pow(3, i));
            } else if (number == 2) {
                result.append(" - ").append((int) Math.pow(3, i));
                count++;
            }
            number = count;


        }

        System.out.println(result.toString());


        //напишите тут ваш код
    }
}