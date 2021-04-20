package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    private int countOfDir = 0;
    private int countOfFiles = 0;
    private int countOfBytes = 0;

    public void setCountOfDir(int countOfDir) {
        this.countOfDir = countOfDir;
    }

    public void setCountOfFiles(int countOfFiles) {
        this.countOfFiles = countOfFiles;
    }

    public void setCountOfBytes(int countOfBytes) {
        this.countOfBytes = countOfBytes;
    }



    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        MyFileVisitor myFileVisitor = new MyFileVisitor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        reader.close();

        if (!Files.isDirectory(Paths.get(text))) {
            System.out.println(text + " - не папка");

        } else {

            Files.walkFileTree(Paths.get(text), myFileVisitor);
        }
        solution.setCountOfDir(myFileVisitor.countOfDir1);
        solution.setCountOfFiles(myFileVisitor.countOfFiles1);
        solution.setCountOfBytes(myFileVisitor.countOfBytes1);
        System.out.println("Всего папок - " + solution.countOfDir);
        System.out.println("Всего файлов - " + solution.countOfFiles);
        System.out.println("Общий размер - " + solution.countOfBytes);

    }


}

class MyFileVisitor extends SimpleFileVisitor<Path> {
    int countOfDir1 = -1;
    int countOfFiles1 = 0;
    int countOfBytes1 = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        byte[] content = Files.readAllBytes(file); // размер файла: content.length

       if (Files.isRegularFile(file)) {
            countOfFiles1++;
            countOfBytes1 = countOfBytes1 + content.length;
        }
       else if (Files.isDirectory(file)){
            postVisitDirectory(file, null);
       }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException{
        countOfDir1++;
        return FileVisitResult.CONTINUE;
    }



}
