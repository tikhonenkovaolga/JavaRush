package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {

        boolean debug = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        if (args.length == 0 || !(args.length == 4 && args[0].equalsIgnoreCase("-c"))) {
            return;
        }

        BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        String line;
        ArrayList<String> list = new ArrayList<>();
        while ((line = buff.readLine()) != null) {
            list.add(line);
        }

        if (debug) {
            for (String a : list) {
                System.out.println(a);
            }
            System.out.println();
        }

        buff.close();

        int maximumIndex = Integer.MIN_VALUE;
        for (int i = 1; i < list.size(); i++) {
            int current = Integer.parseInt(list.get(i).substring(0, 8).trim());
            if (current > maximumIndex) {
                maximumIndex = current;
            }
        }

        String id = String.valueOf(maximumIndex + 1);
        if (debug) {
            System.out.println("Our next index: " + id);
        }

        if (id.length() < 8) {
            int numberOfSpaces = 8 - id.length();
            for (int i = 0; i < numberOfSpaces; i++) {
                id = id + " ";
            }
        }

        String productName = args[1];
        if (productName.length() > 30) {
            productName = productName.substring(0, 30);
        } else {
            int numberOfSpaces = 30 - productName.length();
            for (int i = 0; i < numberOfSpaces; i++) {
                productName = productName + " ";
            }
        }

        String price = args[2];
        if (price.length() > 8) {
            price = price.substring(0, 8);
        } else {
            int numberOfSpaces = 8 - price.length();
            for (int i = 0; i < numberOfSpaces; i++) {
                price = price + " ";
            }
        }


        String quantity = args[3];
        if (quantity.length() > 4) {
            quantity = quantity.substring(0, 4);
        } else {
            int numberOfSpaces = 4 - quantity.length();
            for (int i = 0; i < numberOfSpaces; i++) {
                quantity = quantity + " ";
            }
        }

        String allLine = id + productName + price + quantity;
        list.add(list.size(), allLine);

        if (debug) {
            for (String a : list) {
                System.out.println(a);
            }
        }


        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        for (int i = 0; i < list.size(); i++) {
            fileOutputStream.write(list.get(i).getBytes());
            if (i != list.size() - 1)
                fileOutputStream.write(LINE_SEPARATOR.getBytes());
        }

        fileOutputStream.close();
    }
}
