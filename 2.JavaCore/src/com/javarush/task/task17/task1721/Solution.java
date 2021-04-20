package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String nameFile1 = reader.readLine();
            String nameFile2 = reader.readLine();
            reader.close();

            BufferedReader read1 = new BufferedReader(new FileReader(nameFile1));
            String string1;
            while ((string1 = read1.readLine()) != null){
                allLines.add(string1);
            }
            BufferedReader read2 = new BufferedReader(new FileReader(nameFile2));
            String string2;
            while ((string2 = read2.readLine()) != null){
                forRemoveLines.add(string2);
            }
            read1.close();
            read2.close();

        } catch (IOException e) {

        }


        try {
            new Solution().joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }

    }

    public void joinData() throws CorruptedDataException {

        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        }
                else {
                    allLines.clear();
                    throw new CorruptedDataException();
                }
            }
        }



