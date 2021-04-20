package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (args.length != 0 && args[0].equals("-u") || args[0].equals("-d")) {

            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            String line;
            ArrayList<String> listFile = new ArrayList<>();
            while ((line = buff.readLine()) != null) {
                listFile.add(line);
            }
            buff.close();

//            for (String a : listFile) {
//                System.out.println(a);
//            }
//            System.out.println();

            String id = args[1];
            if (id.length() > 8) {
                id = id.substring(0, 8);
            } else {
                int countOfSpaces = 8 - id.length();
                for (int i = 0; i < countOfSpaces; i++) {
                    id = id + " ";
                }
            }

            for (int i = 0; i < listFile.size(); i++) {
                if ((listFile.get(i).substring(0, 8).trim()).equals(id.trim())) {
                    if (args[0].equals("-u")) {
                        String productName = args[2];
                        if (productName.length() > 30) {
                            productName = productName.substring(0, 30);
                        } else {
                            int numberOfSpaces = 30 - productName.length();
                            for (int j = 0; j < numberOfSpaces; j++) {
                                productName = productName + " ";
                            }
                        }

                        String price = args[3];
                        if (price.length() > 8) {
                            price = price.substring(0, 8);
                        } else {
                            int numberOfSpaces = 8 - price.length();
                            for (int j = 0; j < numberOfSpaces; j++) {
                                price = price + " ";
                            }
                        }


                        String quantity = args[4];
                        if (quantity.length() > 4) {
                            quantity = quantity.substring(0, 4);
                        } else {
                            int numberOfSpaces = 4 - quantity.length();
                            for (int j = 0; j < numberOfSpaces; j++) {
                                quantity = quantity + " ";
                            }
                        }

                        String allLineForU = id + productName + price + quantity;
                        listFile.set(i, allLineForU);

                    } else if (args[0].equals("-d")) {
                        listFile.remove(i);
                    }

                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            final String LINE_SEPARATOR = System.getProperty("line.separator");
            for (int i = 0; i < listFile.size(); i++) {
                fileOutputStream.write(listFile.get(i).getBytes());
                if (i != listFile.size() - 1)
                    fileOutputStream.write(LINE_SEPARATOR.getBytes());
            }

            fileOutputStream.close();


        }

    }
}
