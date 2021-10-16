package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:/logs/"));


     //   System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2022 23:59:59\""));
        System.out.println(logParser.execute("get ip for date = \"05.02.2021 12:22:55\" and date between \"11.12.2013 0:00:00\" and \"03.01.2022 23:59:59\""));
        System.out.println(logParser.execute("get ip for status = \"OK\" and date between \"11.12.2013 0:00:00\" and \"03.01.2022 23:59:59\""));






    }
}