package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Рекурсия для мат. выражения
*/

// expr plusminus | EOF
// потом плюс\минус plusminus : multdiv (+ | -) multdiv
//потом умножение\деление multdiv:  factor | * | \ | (factor)
// потом множитель или число (factor) func | unary | number | expr
//сначала унарные операторы "-" factor
// функция включает имя и аргументы, которых может и не быть; Name (of function) '(' expr , expr ')'

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //       solution.recurse("tan(-30)",0); // -0.58 2
        solution.recurse("2+8*(9/4-1.5)^(1+1)",0); // 6.48 6

    }

    public void recurse(final String expression, int countOperation) {
        //implement
        if (countOperation != 0) {
            return;
        }
        else recurse(expression, countOperation);


        List<Lexeme> lexemes = null;


        double result = 0.0;
        try {
            result = Double.parseDouble(expression);
        } catch (NumberFormatException e) {
            lexemes = lexemeAnalyze(expression);
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            result = expr(lexemeBuffer);
        }

        for (int i = 0; i < lexemes.size(); i++) {

            if (lexemes.get(i).type == LexemeType.EXP | lexemes.get(i).type == LexemeType.OP_DIV | lexemes.get(i).type == LexemeType.OP_MUL | lexemes.get(i).type == LexemeType.OP_PLUS |
                    lexemes.get(i).type == LexemeType.OP_MINUS | lexemes.get(i).type == LexemeType.NAME) {
                countOperation++;
            }
        }


        if (result < 1 & result > -1) {
            DecimalFormat dF = new DecimalFormat("#.##");
            // dF.setRoundingMode(RoundingMode.UP);
            System.out.println(dF.format(result) + " " + countOperation);
        } else {
            System.out.println((int) result + " " + countOperation);
        }


    }


    public enum LexemeType {
        LEFT_BRACKET,
        RIGHT_BRACKET,
        OP_PLUS,
        OP_MINUS,
        OP_MUL,
        OP_DIV,
        NUMBER,
        NAME,
        EXP,
        EOF

    }

    public interface Function {
        double apply(List<Double> args);
    }

    public static HashMap<String, Function> getFunctionMap() {
        HashMap<String, Function> functionHashMap = new HashMap<>();
        functionHashMap.put("sin", args -> {
            if (args.isEmpty() | args.size() > 1) {
                throw new RuntimeException("неверное количество аргументов");
            } else return Math.sin(Math.toRadians(args.get(0)));

        });
        functionHashMap.put("cos", args -> {
            if (args.isEmpty() | args.size() > 1) {
                throw new RuntimeException("неверное количество аргументов");
            } else return Math.cos(Math.toRadians(args.get(0)));
        });

        functionHashMap.put("tan", args -> {
            if (args.isEmpty() | args.size() > 1) {
                throw new RuntimeException("неверное количество аргументов");
            } else return Math.tan(Math.toRadians(args.get(0)));
        });


        return functionHashMap;
    }

    public static class Lexeme {
        private LexemeType type;
        private String value;

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static List<Lexeme> lexemeAnalyze(String expText) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        char[] txt = expText.toCharArray();
        while (pos < txt.length) {
            char c = txt[pos];

            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.EXP, c));
                    pos++;
                    continue;
                default:
                    if (c == ' ') {
                        pos++;
                        continue;
                    }
                    StringBuilder sb1 = new StringBuilder();
                    if (c >= 'a' & c <= 'z' | c >= 'A' & c <= 'Z') {

                        do {
                            sb1.append(c);
                            pos++;
                            if (pos > expText.length()) {
                                break;
                            }
                            c = txt[pos];
                        }
                        while (c >= 'a' & c <= 'z' || c >= 'A' & c <= 'Z');

                        if (getFunctionMap().containsKey(sb1.toString())) {
                            lexemes.add(new Lexeme(LexemeType.NAME, sb1.toString()));
                        }
                    }

                    StringBuilder sb = new StringBuilder();
                    if (c <= '9' && c >= '0') {
                        sb.append(c);
                        if (pos + 1 >= txt.length) {
                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                            pos++;
                            break;
                        } else {
                            pos++;
                            c = txt[pos];
                            if (c == '.') {
                                sb.append(c);
                                pos++;
                                c = txt[pos];
                            }
                            while (c <= '9' & c >= '0' | c == '.' & pos <= expText.length()) {

                                if (!sb.toString().contains(".") & c == '.') {
                                    sb.append(c);
                                    pos++;
                                    c = txt[pos];
                                }
                                sb.append(c);
                                pos++;
                                if (pos < expText.length()) {

                                    c = txt[pos];
                                } else
                                    break;


                            }
                            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                            // pos++;
                            continue;
                        }

                    }


            }
        }

        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;

    }


    public static class LexemeBuffer {

        private int pos;
        private List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }

    }

    public static double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NAME:
                lexemes.back();
                return func(lexemes);
            case NUMBER:
                return Double.parseDouble(lexeme.value);

            case LEFT_BRACKET:
                double value = plusMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("нет правой скобки");
                }
                else return value;

            case OP_MINUS:
                lexeme = lexemes.next();
                if (lexeme.type == LexemeType.NUMBER) {
                    lexemes.back();
                    value = factor(lexemes);
                } else {
                    value = plusMinus(lexemes);
                }

                return -value;

            default:
                throw new RuntimeException("неизвестный символ " + lexeme.value);

        }


    }

    public static double multDiv(LexemeBuffer lexemes) {
        double value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EXP:
                    lexeme = lexemes.next();

                    if (lexeme.type == LexemeType.NUMBER) {
                        lexemes.back();
                        value = Math.pow(value, factor(lexemes));

                    } else if (lexeme.type == LexemeType.LEFT_BRACKET) {
                        value = Math.pow(value, plusMinus(lexemes));

                    }
                    break;
                case RIGHT_BRACKET:
                case EOF:
                case OP_PLUS:
                case OP_MINUS:
                case NAME:

                    lexemes.back();
                    return value;

                default:
                    throw new RuntimeException("wrong" + lexeme.value);
            }
        }

    }

    public static double expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else lexemes.back();
        return plusMinus(lexemes);
    }

    public static double plusMinus(LexemeBuffer lexemes) {
        double value = multDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS:
                    value += multDiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multDiv(lexemes);
                    break;

                case RIGHT_BRACKET:
                case EOF:
                case NAME:
                case EXP:
                    lexemes.back();
                    return value;

                default:
                    throw new RuntimeException("wrong" + lexeme.value);
            }
        }
    }

    public static double func(LexemeBuffer lexemeBuffer) {
        HashMap<String, Function> functionHashMap = getFunctionMap();
        String name = lexemeBuffer.next().value;
        Lexeme lexeme = lexemeBuffer.next();

        if (lexeme.type != LexemeType.LEFT_BRACKET) {
            throw new RuntimeException("wrong argument");
        }

        ArrayList<Double> args = new ArrayList<>();

        lexeme = lexemeBuffer.next();
        if (lexeme.type != LexemeType.RIGHT_BRACKET) {
            lexemeBuffer.back();
            do {
                args.add(expr(lexemeBuffer));
                lexeme = lexemeBuffer.next();

                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("wrong");
                }
            }
            while (lexeme.type != LexemeType.RIGHT_BRACKET);
        }

        return functionHashMap.get(name).apply(args);

    }



    public Solution() {
        //don't delete
    }
}

