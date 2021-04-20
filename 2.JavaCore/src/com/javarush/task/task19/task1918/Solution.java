package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        FileReader file = new FileReader(nameFile);
        BufferedReader buf = new BufferedReader(file);
        String line = "";
        String lineFile = "";
        while ((line = buf.readLine()) != null){
            lineFile = lineFile + line;
        }
        buf.close();
        file.close();

        Document doc = Jsoup.parse(lineFile,"", Parser.xmlParser());
        Elements el = doc.select(args[0]);
        for (Element element: el){
            System.out.println(element);
        }

    }
}