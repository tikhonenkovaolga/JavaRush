package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws Exception {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File newFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, newFile);
        ArrayList<File> fileNames = new ArrayList<>();
        FileOutputStream fos = new FileOutputStream(newFile, true);

        searchPath(path.getPath(), fileNames);

        Collections.sort(fileNames);
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i));
        }


        try {
            for (int i = 0; i < fileNames.size(); i++) {
                FileInputStream fis = new FileInputStream(fileNames.get(i));
                while (fis.available() > 0) {
                    fos.write(fis.read());

                }
                fos.write(System.lineSeparator().getBytes());
                //fos.flush();
                fis.close();
            }
            fos.close();


        } catch (IOException e) {

        }
    }

    public static void searchPath(String path, ArrayList<File> list) {
        File[] fileMas = new File(path).listFiles();
        for (File file1 : fileMas) {

            if (file1.isDirectory()) {
                searchPath(file1.getAbsolutePath(), list);
                continue;
            } else if (file1.isFile() && file1.length() <= 50) {
                list.add(file1);
            }

        }

    }
}
