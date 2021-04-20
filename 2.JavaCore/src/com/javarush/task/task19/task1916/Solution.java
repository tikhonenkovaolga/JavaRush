package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1 = reader.readLine();
        String nameFile2 = reader.readLine();
        reader.close();
        ArrayList<String> listFile1 = new ArrayList<>();
        FileReader file1 = new FileReader(nameFile1);
        BufferedReader buf1 = new BufferedReader(file1);
        String line1 = "";
        while ((line1 = buf1.readLine()) != null) {
            listFile1.add(line1);
        }
        buf1.close();
        file1.close();
        FileReader file2 = new FileReader(nameFile2);
        BufferedReader buf2 = new BufferedReader(file2);
        String line2 = "";
        ArrayList<String> listFile2 = new ArrayList<>();
        while ((line2 = buf2.readLine()) != null) {
            listFile2.add(line2);
        }
        buf2.close();
        file2.close();

        try {
            for (int i = 0; i < listFile1.size(); i++) {
                for (int j = 0; j < listFile2.size(); j++) {
                    if (listFile1.get(i).equals(listFile2.get(j))) {
                        lines.add(new LineItem(Type.SAME, listFile1.get(i)));
                        listFile1.remove(i);
                        listFile2.remove(j);

                        j--;


                    } else if (listFile1.get(i + 1).equals(listFile2.get(j))) {
                        lines.add(new LineItem(Type.REMOVED, listFile1.get(i)));
                      //  lines.add(new LineItem(Type.SAME, listFile2.get(j)));

                        listFile1.remove(i);

                        j--;



                    } else if (listFile1.get(i).equals(listFile2.get(j + 1))) {
                        lines.add(new LineItem(Type.ADDED, listFile2.get(j)));
                       // lines.add(new LineItem(Type.SAME, listFile1.get(i)));
                        listFile2.remove(j);

                        j--;




                    }
                    else lines.add(new LineItem(Type.REMOVED, listFile1.get(i)));

                }


            }

        } catch (IndexOutOfBoundsException e) {
            if (listFile1.size() > listFile2.size()) {
                lines.add(new LineItem(Type.REMOVED, listFile1.get(listFile1.size() - 1)));
            } else lines.add(new LineItem(Type.ADDED, listFile2.get(listFile2.size() - 1)));
        }


        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }


    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
