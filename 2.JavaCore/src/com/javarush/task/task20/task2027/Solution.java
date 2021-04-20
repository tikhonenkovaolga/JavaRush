package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'e', 'e', 'e', 'l', 'e'},
                {'u', 's', 'n', 'n', 'n', 'h'},
                {'l', 'e', 'n', 'o', 'n', 'e'},
                {'m', 'm', 'n', 'n', 'n', 'h'},
                {'p', 'e', 'e', 'e', 'j', 'e'}
        };

        detectAllWords(crossword, "one");


        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {


        ArrayList<Word> startLetters = findStartOfWord(crossword, words);

        ArrayList<Word> dataWords = checkWords(crossword, startLetters);

       // System.out.println(dataWords.size());
        for (int i = 0; i < dataWords.size(); i++){
            System.out.println(dataWords.get(i).toString());
          }


        return dataWords;
    }


    private static ArrayList<Word> findStartOfWord(int[][] crossword, String[] strings) {
        ArrayList<Word> start = new ArrayList<>();
        Word word;
        for (int k = 0; k < strings.length; k++) {
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    String currentSymbol = new String(new char[]{(char) crossword[i][j]});
                    if (strings[k].substring(0, 1).equalsIgnoreCase(currentSymbol)) {
                        word = new Word(strings[k]);
                        word.startX = j;
                        word.startY = i;
                        word.setStartPoint(word.startX, word.startY);
                        start.add(word);
                    }
                }
            }
        }

//        for (int i = 0; i < start.size(); i++) {
//            System.out.println(start.get(i).toString());
//        }
//        System.out.println();
        return start;

    }


    private static ArrayList<Word> checkWords(int[][] crossword, ArrayList<Word> list) {
        ArrayList<Word> dataWords = new ArrayList<>();
        String searchWord = "";
        for (int i = 0; i < list.size(); i++) {
            searchWord = list.get(i).text.substring(0, 1);
            int x = list.get(i).startX;
            int y = list.get(i).startY;
            char[] letters = list.get(i).text.toCharArray();

            for (int j = 1; j < letters.length; j++) {
                if (x + j < crossword[0].length && crossword[y][x + j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x + (searchWord.length() - 1) , y);
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);

            //System.out.println(dataWords.get(0).toString());


            for (int j = 1; j < letters.length; j++) {

                if (x - j >= 0 && crossword[y][x - j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x - (searchWord.length() - 1) , y);
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);



            for (int j = 1; j < letters.length; j++) {
                if (y + j < crossword.length && crossword[y + j][x] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x , y + (searchWord.length() - 1));
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);



            for (int j = 1; j < letters.length; j++) {
                if (y - j >= 0 && crossword[y - j][x] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x , y - (searchWord.length() - 1));
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);

     //       System.out.println(dataWords.get(0).toString());

            for (int j = 1; j < letters.length; j++) {
                if (x - j >= 0 && y - j >= 0 && crossword[y - j][x - j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x - (searchWord.length() - 1) , y - (searchWord.length() - 1));
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);

            for (int j = 1; j < letters.length; j++) {
                if (x + j < crossword[0].length && y + j < crossword.length && crossword[y + j][x + j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x + (searchWord.length() - 1) , y + (searchWord.length() - 1));
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);


            for (int j = 1; j < letters.length; j++) {
                if (x + j < crossword[0].length && y - j >= 0 && crossword[y - j][x + j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                }
                else break;
            }
            if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                Word word = new Word(list.get(i).text);
                word.setStartPoint(x, y);
                word.setEndPoint(x + (searchWord.length() - 1) , y - (searchWord.length() - 1));
                dataWords.add(word);
                searchWord = list.get(i).text.substring(0, 1);


            } else searchWord = list.get(i).text.substring(0, 1);


            for (int j = 1; j < letters.length; j++) {
                if (x - j >= 0 && y + j < crossword.length && crossword[y + j][x - j] == letters[j]) {
                    searchWord = searchWord + new String(new char[]{letters[j]});
                } else break;
            }

                if (searchWord.equalsIgnoreCase(list.get(i).text)) {
                    Word word = new Word(list.get(i).text);
                    word.setStartPoint(x, y);
                    word.setEndPoint(x - (searchWord.length() - 1) , y + (searchWord.length() - 1));
                    dataWords.add(word);
                    searchWord = list.get(i).text.substring(0, 1);

                } else searchWord = list.get(i).text.substring(0, 1);



        }
        return dataWords;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
