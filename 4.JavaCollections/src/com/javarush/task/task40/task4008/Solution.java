package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String dateTime = "\\d{1,2}.\\d{1,2}.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
        String justDate = "\\d{1,2}.\\d{1,2}.\\d{4}";
        String justTime = "\\d{1,2}:\\d{1,2}:\\d{2}";

            if (date.matches(dateTime)) {
                String[] strings = date.split(" ");
                parseDate(strings[0]);
                parseTime(strings[1]);
            } else if (date.matches(justDate)) {
                parseDate(date);
            } else if (date.matches(justTime)) {
                parseTime(date);
            }

    }

    public static void parseDate(String date){
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter1);
        System.out.println("День: " + localDate.getDayOfMonth());
        System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
        System.out.println("День месяца: " + localDate.getDayOfMonth());
        System.out.println("День года: " + localDate.getDayOfYear());
        System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
        System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
        System.out.println("Месяц: " + localDate.getMonth().getValue());
        System.out.println("Год: " + localDate.getYear());


    }

    public static void parseTime(String time)  {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:m:s");
        LocalTime localTime = LocalTime.parse(time, formatter2);
        System.out.println("AM или PM: " + (localTime.getHour() > 12 ? "PM" : "AM"));
        System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Часы дня: " + localTime.getHour());
        System.out.println("Минуты: " + localTime.getMinute());
        System.out.println("Секунды: " + localTime.getSecond());

    }





    }

