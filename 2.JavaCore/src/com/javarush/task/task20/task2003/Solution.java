


package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {

    public static Map<String, String> properties = new HashMap<>();


    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(nameFile);
        load(fileInputStream);
        fileInputStream.close();

    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop1 = new Properties();
        prop1.putAll(properties);
        prop1.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
      Properties prop = new Properties();
      prop.load(inputStream);//выгружаем данные из файла
      for (String name : prop.stringPropertyNames()){
          properties.put(name, prop.getProperty(name));//вносим данные в карту
      }
      for (Map.Entry<String, String> entry : properties.entrySet()){
          System.out.println(entry.getKey() + entry.getValue());
      }

    }

    public static void main(String[] args) throws Exception{
        new Solution().fillInPropertiesMap();


    }
}
