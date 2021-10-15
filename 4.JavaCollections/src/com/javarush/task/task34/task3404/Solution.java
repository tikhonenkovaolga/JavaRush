package com.javarush.task.task34.task3404;

/*
Рекурсия для мат. выражения
*/


import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //       solution.recurse("tan(-30)",0); // -0.58 2
        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0); // 6.48 6

    }

    public double recurse(final String expression, int countOperation) {
        //implement
        if (!isBracketsEqual(expression)) throw new RuntimeException("скобки не закрыты");
        double result = 0.0;
        String pattern = expression.replace(" ", "");
        char[] symbols = pattern.toCharArray();
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == '+' || symbols[i] == '-' || symbols[i] == '*' || symbols[i] == '/' || symbols[i] == '^' || symbols[i] == 's' ||
                    symbols[i] == 'c' || symbols[i] == 't') {
                countOperation++;
            }

        }


        try {
            result = Double.parseDouble(expression);
        } catch (NumberFormatException e) {
            result = calculus(pattern);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String result1 = decimalFormat.format(result);
            System.out.print(result1 + " " + countOperation);
        }


        return result;
    }

    static double calculus(String pattern) {
        if (pattern.charAt(0) == '-') calculus("0" + pattern);
        pattern = removeBrackets(pattern);
        int indOp = indexOfOperator(pattern);
        if (indOp == -1) {
            try {
                return mathOperator(pattern);
            } catch (Exception e) {

            }
        }

        switch (pattern.toCharArray()[indOp]) {
            case '*':
                return calculus(pattern.substring(0, indOp)) * calculus(pattern.substring(indOp + 1));
            case '-':
                return calculus(pattern.substring(0, indOp)) - calculus(pattern.substring(indOp + 1));
            case '/':
                return calculus(pattern.substring(0, indOp)) / calculus(pattern.substring(indOp + 1));
            case '+':
                return calculus(pattern.substring(0, indOp)) + calculus(pattern.substring(indOp + 1));
            case '^':
                return Math.pow(calculus(pattern.substring(0, indOp)), calculus(pattern.substring(indOp + 1)));
            default:
                return 0;
        }


    }

    private static int countSymbol(char symbol, String pattern) {
        int countS = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == symbol) {
                countS++;
            }
        }
        return countS;
    }

    private static boolean isBracketsEqual(String pattern) {
        return (countSymbol('(', pattern) == countSymbol(')', pattern));
    }

    private static int indexOfCharacter(char symbol, String patternString, boolean fromBegin) {
        int index = -1;
        int flag = 0; //если флаг -1, то скобка открывается, если флаг +1, то скобка закрывается, если 0, то не скобка
        char[] pattern = patternString.toCharArray();
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == symbol && flag == 0) {
                if (fromBegin) return i;
                else index = i;
                if (symbol == '(') flag--;
                if (symbol == ')') flag++;
            }
        }
        return index;
    }

    private static int indexOfOperator(String pattern) {
        int indOp;
        if ((indOp = (indexOfCharacter('+', pattern, false))) != -1) return indOp;
        if ((indOp = (indexOfCharacter('-', pattern, false))) != -1) return indOp == 0 ? -1 : indOp;
        if ((indOp = (indexOfCharacter('*', pattern, false))) != -1) return indOp;
        if ((indOp = (indexOfCharacter('/', pattern, false))) != -1) return indOp;
        if ((indOp = (indexOfCharacter('^', pattern, false))) != -1) return indOp;
        return -1;
    }

    //если выражение закрыто скобками-убираем скобки
    private static String removeBrackets(String patternString) {
        int indOp = indexOfOperator(patternString);
        if (patternString.length() > 1 && indOp == -1) {
            if (patternString.charAt(0) == '(' && patternString.charAt(patternString.length() - 1) == ')') {
                patternString = removeBrackets(patternString.substring(1, patternString.length() - 1));
            }
        }
        return patternString;
    }

    //убираем из строки название функции
    private static String cutPattern(String pattern, int length) {
        return pattern.substring(length, pattern.length() - 1);
    }

    private static int switchOperator(String pattern) {
        switch (pattern) {
            case "sin":
                return 1;
            case "cos":
                return 2;
            case "tan":
                return 3;
        }
        return 0;
    }

    private static double mathOperator(String pattern) throws Exception {
        double sum = 0;
        String[] parts;
        String operat;
        try {
            parts = pattern.split("\\(");
            operat = parts[0];
            switch (switchOperator(operat)) {
                case 1:
                    pattern = cutPattern(pattern, 4);
                    sum += Math.sin(Math.toRadians(calculus(pattern)));
                    break;
                case 2:
                    pattern = cutPattern(pattern, 4);
                    sum += Math.cos(Math.toRadians(calculus(pattern)));
                    break;
                case 3:
                    pattern = cutPattern(pattern, 4);
                    sum += Math.tan(Math.toRadians(calculus(pattern)));
                    break;
            }
        } catch (Exception e) {

        }
        return sum;
    }


    public Solution() {
        //don't delete
    }
}

