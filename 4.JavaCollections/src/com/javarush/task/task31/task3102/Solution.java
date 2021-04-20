package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> allList = new PriorityQueue<>();
        List<String> listFiles = new ArrayList<>();
        File[] files = new File(root).listFiles();

        Collections.addAll(allList, files);

        while (!allList.isEmpty()){
            File current = allList.poll();
            if (current.isFile()) {
                listFiles.add(current.getAbsolutePath());

            } else if (current.isDirectory()) {
                Collections.addAll(allList, current.listFiles());
                }
            }

        for (int i = 0; i < listFiles.size(); i++){
            System.out.println(listFiles.get(i));
        }


        return listFiles;

    }

    public static void main(String[] args) throws IOException{
        getFileTree("C://Users//Оля//Desktop//path");



    }
}
