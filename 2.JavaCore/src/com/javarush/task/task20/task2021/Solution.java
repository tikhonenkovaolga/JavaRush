package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream out) throws IOException{
            throw new NotSerializableException("NO");
        }
        private void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
            throw new NotSerializableException("NO");
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        SubSolution subSolution = new SubSolution();

        FileOutputStream fos = new FileOutputStream("C://Users//Оля//Desktop//test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(subSolution);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("C://Users//Оля//Desktop//test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SubSolution subSolution1 = (SubSolution)ois.readObject();
        ois.close();
        fis.close();


    }
}
