package com.javarush.task.task40.task4011;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/* 
Свойства URL
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        decodeURLString("htt/index.htm?language=en#j2se");
    }

    public static void decodeURLString(String s) {
        try{
            URL url = new URL(s);
            String protocol = url.getProtocol();
            System.out.println(protocol);
            String authority = url.getAuthority();
            System.out.println(authority);
            String file = url.getFile();
            System.out.println(file);
            String host = url.getHost();
            System.out.println(host);
            String path = url.getPath();
            System.out.println(path);
            int port = url.getPort();
            System.out.println(port);
            int defaultPort = url.getDefaultPort();
            System.out.println(defaultPort);
            String query = url.getQuery();
            System.out.println(query);
            String ref = url.getRef();
            System.out.println(ref);
        }
        catch (IOException e){
            System.out.println(String.format("Parameter %s is not a valid URL.", s));
        }


    }
}

