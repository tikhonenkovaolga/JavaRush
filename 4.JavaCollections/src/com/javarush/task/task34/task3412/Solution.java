package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug("значение value изменено");
    }

    public static void main(String[] args) {
        Solution solution = new Solution(2,"z",new Date());
        solution.calculateAndSetValue3(3);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(3,2);

    }

    public void calculateAndSetValue3(long value) {
        logger.trace("отслеживаем работу метода calculateAndSetValue3");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("значение value1 изменено");
        } else {
            value1 = (int) value;
            logger.debug("значение value1 изменено");
        }
    }

    public void printString() {
        logger.trace("отслеживаем работу метода printString");
        if (value2 != null) {
            System.out.println(value2.length());

        }
    }

    public void printDateAsLong() {
        logger.trace("отслеживаем работу метода printDateAsLong");
        if (value3 != null) {
            System.out.println(value3.getTime());

        }
    }

    public void divide(int number1, int number2) {
        logger.trace("отслеживаем работу метода divide");
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error("ошибка вычисления");
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        logger.debug("значение value1 изменено");
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        logger.debug("значение value2 изменено");
    }

    public void setValue3(Date value3) {
        this.value3 = value3;
        logger.debug("значение value3 изменено");
    }
}
