package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C://path"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        String[] urlStr = urlString.split("/");
        String nameFile = urlStr[urlStr.length-1];
        if (Files.notExists(downloadDirectory)){
            Files.createDirectory(downloadDirectory);
        }
        Path downloadFile = Paths.get(downloadDirectory.toString() + File.pathSeparator + nameFile);
        Path tempFile = Files.createTempFile("temp", "tmp");
        Files.copy(inputStream, tempFile, REPLACE_EXISTING);
        inputStream.close();

        Files.move(tempFile, downloadFile, REPLACE_EXISTING);

        return downloadFile;
    }
}
