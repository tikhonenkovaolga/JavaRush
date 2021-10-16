package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            String urlHost = url.getHost();

            Socket socket = new Socket(urlHost, 80);

            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);
            out.println("GET "+url.getFile()+" HTTP/1.1\r\n");
            out.println("Host: \"+url.getHost()+\"\\r\\n\\r\\n");
            out.println("");
            out.flush();

            InputStream is = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String responseLine = bufferedReader.readLine();
            System.out.println(responseLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}