package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException{
      PersonScanner ps = new PersonScannerAdapter(new Scanner(new File("C://Users//Оля//Desktop//test.txt")));
      ps.read();
        System.out.println(ps);
        ps.close();


    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {

            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String[]arr = line.split("\\s", 4);
            String lastName = arr[0];
            //System.out.println(lastName);
            String firstName = arr[1];
           // System.out.println(firstName);
            String middleName = arr[2];
           // System.out.println(middleName);
            String stringDate = arr[3];
           // System.out.println(stringDate);
            Date birthDate = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
                birthDate = format.parse(stringDate);
             //   System.out.println(birthDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return new Person(firstName, middleName, lastName, birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
