package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("C://Users//Оля//Desktop//test.txt", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            User user = new User();
            user.setFirstName("Kolya");
            user.setLastName("Petrov");
            Date date = new Date(21212000);
            user.setBirthDate(date);
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);

            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            if (javaRush.hashCode() == loadedObject.hashCode()){
                System.out.println("==");
            }
            else System.out.println("!=");
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            String isUsersPresent = !users.isEmpty() ? "yes" : "no";
            writer.println(isUsersPresent);
            writer.flush();
            if (!users.isEmpty()){
                for (int i = 0; i < users.size(); i++){
                    writer.println(users.get(i).getFirstName());
                    writer.println(users.get(i).getLastName());
                    writer.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").format(users.get(i).getBirthDate()));
                    writer.println(users.get(i).isMale());
                    writer.println(users.get(i).getCountry().toString());

                }
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isUsersPresent = reader.readLine();
            if (isUsersPresent.equals("yes")){
                while (reader.ready()){
                    String firstName = reader.readLine();
                    String lastName = reader.readLine();
                    String bd = reader.readLine();
                    String sex = reader.readLine();
                    String country = reader.readLine();
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setBirthDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").parse(bd));
                    user.setMale(Boolean.parseBoolean(sex));
                    user.setCountry(User.Country.valueOf(country));

                this.users.add(user);
                }

            }
            reader.close();
            for (int i = 0; i < users.size(); i++){
                System.out.println(users.get(i));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
