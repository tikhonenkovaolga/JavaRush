package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if ((n==null) || !(n instanceof Solution)) {
            return false;
        }
        if (n == this){
            return true;
        }
        Solution sol = (Solution) n;

        return ((sol.first==first) || (first != null &&  sol.first.equals(first))) && ((sol.last==last) || (last != null && sol.last.equals(last)));

    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((last == null) ? 0 : last.hashCode());

        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
//31 было выбрано так как это нечётное простое число.
//
//Если вопрос в том, почему именно 31, то это потому что
// операция умножения может быть заменена сдвигом и вычитанием для повышения производительности: 31 * i == (i << 5) - i.
// Современные виртуальные машины делают такого рода оптимизации автоматически.
