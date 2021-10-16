package com.javarush.task;

import javax.mail.*;
import javax.mail.internet.*;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException, MessagingException {
        //копирование файла из интернета
//        URL url = new URL("https://www.google.com.ua/images/srpr/logo11w.png");
//        InputStream is = url.openStream();
//        Files.copy(is, new File("C:/Users/Оля/Desktop/page").toPath());

        //отправка письма самой себе
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.host", "smtp.mail.ru");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        message.setSubject("test");
        message.setText("Hello baby!!!");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("tyryshkina@mail.ru"));
        message.setSentDate(new Date());
        message.addFrom(new Address[]{new InternetAddress("tyryshkina@mail.ru")});

        String userLogin = "tyryshkina@mail.ru";
        String pass = "yjz,hm_2018";
        Transport transport = session.getTransport();
        transport.connect(userLogin, pass);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));




    }
}
