package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        int id;
        String name;
        Sex sex;
        Date bd;
        Person person;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        switch (args[0]){
            case "-c":
                synchronized (allPeople){
                for (int i = 1; i < args.length-2; i+=3){
                   name = args[i];
                   sex = (args[i + 1].equals("м"))? Sex.MALE : Sex.FEMALE;
                   bd = format.parse(args[i + 2]);
                   if (sex == Sex.MALE){
                       person = Person.createMale(name, bd);
                   }
                   else {
                       person = Person.createFemale(name, bd);
                   }
                   allPeople.add(person);
                    System.out.println(allPeople.indexOf(person));

                    }
                break;
                }
            case "-u":
                synchronized (allPeople){
                    for (int i = 1; i < args.length - 3; i+= 4){

                        if (args[i + 2].equals("м")){
                            allPeople.set(Integer.parseInt(args[i]), Person.createMale(args[i+1], format.parse(args[i+3])));
                        }
                        else {
                            allPeople.set(Integer.parseInt(args[i]), Person.createFemale(args[i+1], format.parse(args[i+3])));
                        }

                    }
                    break;
                }
            case "-d":
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++){
                        id = Integer.parseInt(args[i]);
                        person = allPeople.get(id);
                        person.setBirthDate(null);
                        person.setName(null);
                        person.setSex(null);
                   }
                    break;
                }
            case "-i":
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyy", Locale.ENGLISH);
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        name = person.getName();
                        String sex1 = (person.getSex().equals(Sex.MALE))? "м" : "ж";
                        System.out.println(name + " " + sex1 + " " + format1.format(person.getBirthDate()));

                    }
                    break;
                }
        }
    }
}
