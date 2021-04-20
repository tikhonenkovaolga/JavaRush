package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // List of addresses
        List<String> addresses = new ArrayList<>();
        while (true) {

            //String town = reader.readLine();
            String family = reader.readLine();
            //if (town.isEmpty()) break;
            if (family.isEmpty()) break;

            //addresses.add(town);
            addresses.add(family);
        }

        String town = reader.readLine();
        for (int i = 0; i < addresses.size(); i++){
            if (town.equals(addresses.get(i))){
                System.out.println(addresses.get(i + 1));
            }
        }


        // Read the house number
//        int houseNumber = Integer.parseInt(reader.readLine());
//
//        if (0 <= houseNumber && houseNumber < addresses.size()) {
//            String familyName = addresses.get(houseNumber);
//            System.out.println(familyName);
//        }
    }
}
