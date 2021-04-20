package com.javarush.task.task10.task1001;

/* 
Задача №1 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        int a = 0;
        int b = (byte) a + 46;
        //System.out.println(b);
        byte c = (byte) (a * b);
       // System.out.println(c);
        double f = (char) 1234.15;
//        System.out.println(f);
//        System.out.println(f/c);
//        System.out.println(c + b);
        long d = (char) (a + f / c + b);
        System.out.println(d);
    }
}
//1. Программа должна выводить текст на экран.
//        2. Нельзя менять команду вывода на экран.
//        3. Метод main() должен содержать переменную a типа int.
//        4. Метод main() должен содержать переменную b типа int.
//        5. Метод main() должен содержать переменную c типа byte.
//        6. Метод main() должен содержать переменную f типа double.
//        7. Метод main() должен содержать переменную d типа long.
//        8. Начальное значение переменных при инициализации менять нельзя. Можно только редактировать операторы приведения типа (добавлять/удалять/изменять).
//        9. Программа должна выводить число больше, чем 0.