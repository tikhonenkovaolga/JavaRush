package com.javarush.task;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws IOException, MessagingException {
        //копирование файла из интернета
//        URL url = new URL("https://www.google.com.ua/images/srpr/logo11w.png");
//        InputStream is = url.openStream();
//        Files.copy(is, new File("C:/Users/Оля/Desktop/page").toPath());

        //отправка письма самой себе
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "smtps");
//        properties.put("mail.host", "smtp.mail.ru");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//        Session session = Session.getDefaultInstance(properties);
//
//        MimeMessage message = new MimeMessage(session);
//        message.setSubject("test");
//        message.setText("Hello baby!!!");
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress("tyryshkina@mail.ru"));
//        message.setSentDate(new Date());
//        message.addFrom(new Address[]{new InternetAddress("tyryshkina@mail.ru")});
//
//        String userLogin = "tyryshkina@mail.ru";
//        String pass = "yjz,hm_2018";
//        Transport transport = session.getTransport();
//        transport.connect(userLogin, pass);
//        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));


       // работа со stream

        List<String> list = new ArrayList<>();
        String str = "dsergvcdfgvx";
        for (int i = 0; i < 10; i++){
            list.add(str);
            str = str.substring(1);
        }

        Stream stream = list.stream();
        
       // stream.filter(x -> x.toString().length() == 3).forEach(System.out ::println);

       // stream.forEach(x->System.out.println(x));

        stream.filter(x->x.toString().matches("cd")).forEach(System.out::println);

//        String[] array = {"Java", "Ruuuuussshhh"};
//        Stream<String> streamOfArray = Arrays.stream(array);
//        streamOfArray.map(s->s.split("")) //Преобразование слова в массив букв
//                .flatMap(Arrays::stream).distinct() //выравнивает каждый сгенерированный поток в один поток
//                .collect(Collectors.toList()).forEach(System.out::println);


        String[] array = {"Java", "Ruuuuussshhh"};
        Stream<String> streamOfArray = Arrays.stream(array);
        streamOfArray.map(s->s.split("")) //Преобразование слова в массив букв
                .map(Arrays::stream).distinct() //Сделать массив в отдельный поток
                .collect(Collectors.toList()).forEach(System.out::println);








    }
}
