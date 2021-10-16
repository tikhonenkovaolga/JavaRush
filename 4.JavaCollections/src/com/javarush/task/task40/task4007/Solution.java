package com.javarush.task.task40.task4007;

/*
Работа с датами
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        String dateTime = "\\d{1,2}.\\d{1,2}.\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";
        String justDate = "\\d{1,2}.\\d{1,2}.\\d{4}";
        String justTime = "\\d{1,2}:\\d{1,2}:\\d{2}";
        try {
            if (date.matches(dateTime)) {
                String[] strings = date.split(" ");
                parseDate(strings[0], calendar);
                parseTime(strings[1], calendar);
            } else if (date.matches(justDate)) {
                parseDate(date, calendar);
            } else if (date.matches(justTime)) {
                parseTime(date, calendar);
            }
        }
        catch (ParseException e){

        }

        //напишите тут ваш код
    }

    public static void parseDate(String date, Calendar calendar) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        calendar.setTime(format.parse(date));
        System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День недели: " + (calendar.get(Calendar.DAY_OF_WEEK)== 1 ? 7 : (calendar.get(Calendar.DAY_OF_WEEK) - 1)));
        System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("Год: " + calendar.get(Calendar.YEAR));

    }

    public static void parseTime(String time, Calendar calendar) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        calendar.setTime(format.parse(time));
        System.out.println("AM или PM: " + (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM": "PM"));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));

    }


}
