package com.javarush.task.task20.task2020;

import java.io.*;
import java.util.logging.Logger;

/* 
Сериализация человека
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greeting;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greeting = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Person person = new Person("Bob", "Marley", "USA", Sex.MALE);
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Оля\\Desktop\\test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.close();
        fos.close();
        FileInputStream fis = new FileInputStream("C:\\Users\\Оля\\Desktop\\test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person newPerson = (Person)ois.readObject();
        ois.close();
        fis.close();
        System.out.println(newPerson.firstName + " " + newPerson.lastName + " " + newPerson.fullName + " " + newPerson.greeting + " " + newPerson.country + " " + newPerson.sex);

    }
}
