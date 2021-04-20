package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path newFile = Paths.get(args[0]);
        Path zipPath = Paths.get(args[1]);
        Path newPath = Paths.get("//new" + newFile.getFileName().toString());
        FileInputStream fis = new FileInputStream(args[1]);
        ZipInputStream zis = new ZipInputStream(fis);

        ZipEntry entry;
        String name ;

        HashMap<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
        while ((entry = zis.getNextEntry())!= null) {
            byte[] bytes = new byte[1024];

            if (!entry.getName().equals(newFile.getFileName().toString())){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = zis.read(bytes)) != -1) {
                bos.write(bytes, 0, count);
            }
                bos.close();
                map.put(entry, bos);

            }

        }

        zis.close();
        fis.close();



        FileOutputStream fos = new FileOutputStream(args[1]);
        ZipOutputStream zos = new ZipOutputStream(fos);

        zos.putNextEntry(new ZipEntry(newPath.toString()));
        Files.copy(newFile, zos);

        for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry1 : map.entrySet()) {
            zos.putNextEntry(new ZipEntry(entry1.getKey().getName()));
            zos.write(entry1.getValue().toByteArray());
        }





        fos.flush();

        fos.close();
        zos.close();


    }


}

