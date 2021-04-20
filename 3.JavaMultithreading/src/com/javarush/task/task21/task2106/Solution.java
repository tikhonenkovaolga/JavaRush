package com.javarush.task.task21.task2106;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* 
Ошибка в equals/hashCode
*/
public class Solution {
    public Solution(){

    }
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Solution)){
            return false;
        }
        if (this == o) return true;

        Solution solution1 = (Solution) o;

        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (anInt != 0 && anInt != solution1.anInt) return false;
        if (date != null ? !date.equals(solution1.date): false) ;
        if (solution != null ? !solution.equals(solution1.solution) : false);
        if (string != null ? !string.equals(solution1.string) : false) ;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        long temp;
        result = 31* result + anInt;
        result = 31*result +((string == null) ? 0 : string.hashCode());
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31*result + ((date == null) ? 0 : date.hashCode());
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(new Solution(0, "", 0d, date, null)
                .equals(new Solution(0, "", 0d, date, null)));

    }
}
