package com.javarush.task.task20.task2014;

import java.io.*;
import java.security.cert.PKIXRevocationChecker;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(nameFile);
        FileOutputStream outputStream = new FileOutputStream(nameFile);

        Solution savedObject = new Solution(22);
        //savedObject.currentDate = 22.03.2001;
        savedObject.string = "olha";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedObject);
        outputStream.close();
        objectOutputStream.close();


        Solution loadObject;
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        loadObject = (Solution)objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(new Solution(4));
        System.out.println(loadObject);
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }


    }

