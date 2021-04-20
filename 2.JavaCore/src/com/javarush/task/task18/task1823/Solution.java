package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        while (!fileName.equals("exit")){
            ReadThread readThread = new ReadThread(fileName);
            readThread.start();
            fileName = reader.readLine();
        }
        reader.close();


    }

    public static class ReadThread extends Thread {
        private String nameFile;
        public ReadThread(String fileName) {

            this.nameFile = fileName;
        }
        ArrayList<Integer>list = new ArrayList<>();
        HashMap<Integer, Integer>map = new HashMap<>();

        @Override
        public void run() {
            try {
                FileInputStream file = new FileInputStream(nameFile);
                while (file.available() > 0) {
                    list.add(file.read());
                }
                file.close();
                int count = 0;
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(i) == list.get(j)) {
                            count = Collections.frequency(list, list.get(i));
                        }
                    }
                    map.put(list.get(i), count);

                }
                int maxValue = Collections.max(map.values());
                for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == maxValue){
                        resultMap.put(nameFile, entry.getKey());
                    }
                }

            }

            catch (IOException e){

            }

        }
    }
}
