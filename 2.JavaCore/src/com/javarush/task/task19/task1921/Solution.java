package com.javarush.task.task19.task1921;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> allFile = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        FileReader file = new FileReader(args[0]);
        BufferedReader buf = new BufferedReader(file);
        String line = "";
        String names = "";
        String dates = "";
        while ((line = buf.readLine()) != null) {
            allFile.add(line);

        }
        buf.close();
        file.close();
        for (int i = 0; i < allFile.size(); i++){
            if (allFile.get(i).isEmpty()){
                allFile.remove(i);
            }
        }

        for (int i = 0; i < allFile.size(); i++){
            String[] arrLine = allFile.get(i).split("\\s");
            for (int j = 0; j < arrLine.length - 3; j++) {
                names = names + arrLine[j].trim() + " ";
            }
            name.add(names);
            names = "";


            for (int j = arrLine.length - 3; j < arrLine.length; j++) {
                dates = dates + arrLine[j].trim() + " ";
            }
            date.add(dates);
            dates = "";
        }


        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
        for (int i = 0; i < name.size(); i++){
            try {
                PEOPLE.add(new Person(name.get(i).trim(), format.parse(date.get(i))));
            }
            catch (ParseException e){

            }

        }




    }
}
