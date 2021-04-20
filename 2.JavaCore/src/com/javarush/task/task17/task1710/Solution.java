package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        int id;
        Person person;
        Sex sex;
        Date birthDate;
        String name;


        try {
            switch (args[0]) {
                case "-c":
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    name = args[1];
                    sex = (args[2].equals("м"))? Sex.MALE : Sex.FEMALE;
                    birthDate = format.parse(args[3]);
                    if (sex == Sex.MALE){
                        person = Person.createMale(name, birthDate);
                    }
                    else {
                        person = Person.createFemale(name, birthDate);
                    }
                    allPeople.add(person);

                    System.out.println(allPeople.indexOf(person));
                    break;
                case "-u":
                    DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    if (args[3].equals("м")) {
                        allPeople.set((Integer.parseInt(args[1])), Person.createMale(args[2], format1.parse(args[4])));
                    } else {
                        allPeople.set((Integer.parseInt(args[1])), Person.createFemale(args[2], format1.parse(args[4])));
                    }
                    break;
                case "-d":
                    id = Integer.parseInt(args[1]);
                    person = allPeople.get(id);
                    person.setName(null);
                    person.setSex(null);
                    person.setBirthDate(null);


                    break;
                case "-i":
                    id = Integer.parseInt(args[1]);
                    person = allPeople.get(id);
                    String sex1 = (person.getSex().equals(Sex.MALE))? "м" : "ж";
                    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    System.out.println(person.getName() + " " + sex1 + " " + format2.format(person.getBirthDate()));
                    break;
                }


        }
        catch (ParseException e){

        }

    }
}
