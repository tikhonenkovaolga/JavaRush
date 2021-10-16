package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("1.12.2015", "2016"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
        LocalDate localDate = LocalDate.parse(birthday, formatter1);
        Year year1 = Year.parse(year, formatter2);

        return localDate.withYear(year1.getValue()).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
