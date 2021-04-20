package com.javarush.task.task22.task2212;

import java.util.ArrayList;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.length() == 0 || telNumber.matches("\\W")){
            return false;
        }
        String tmp = telNumber;
        int length = tmp.replaceAll("\\D", "").length();
        if (length == 12){
            return telNumber.matches("(^\\+{1})\\d*(\\(\\d{3}\\))?\\d*(-?\\d+)-?\\d+\\d$");
        }
        else if(length == 10){
            return telNumber.matches("^(\\d|\\(\\d{3}\\))\\d*\\d*(-?\\d+)-?\\d+\\d$");
        }

        else return false;
    }

    public static void main(String[] args) {
        ArrayList<String> checkList = new ArrayList<>();
        checkList.add("+380501234567");
        checkList.add("+38(050)1234567");
        checkList.add("+38050123-45-67");
        checkList.add("050123-4567");
        checkList.add("+38)050(1234567");
        checkList.add("+38(050)1-23-45-6-7");
        checkList.add("050ххх4567");
        checkList.add("050123456");
        checkList.add("(0)501234567");
        for (String s:checkList) {
            System.out.println(checkTelNumber(s));
        }
    }
}
