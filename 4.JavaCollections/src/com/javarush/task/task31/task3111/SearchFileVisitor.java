package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = null;
    private String partOfContent = null;
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;

    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;


    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;


    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
      

    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        byte[] content = Files.readAllBytes(file); // размер файла: content.length


        if ((partOfName == null || file.getFileName().toString().contains(partOfName)) &&
                (minSize == -1 || Files.readAllBytes(file).length > minSize) &&
                (maxSize == -1 || Files.readAllBytes(file).length < maxSize) &&
                (partOfContent == null ||
                        new String(Files.readAllBytes(file)).contains(partOfContent))) {
            foundFiles.add(file);
        }


        //    }


        return FileVisitResult.CONTINUE;
    }


}
