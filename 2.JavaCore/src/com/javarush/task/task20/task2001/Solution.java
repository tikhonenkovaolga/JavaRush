package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("C://Users//Оля//Desktop//test.txt", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if (ivanov.hashCode() == somePerson.hashCode()) {
                System.out.println("объекты равны");
            } else System.out.println("объекты не равны");


        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            String isNamePresent = name != null ? "yes" : "no";
            writer.println(isNamePresent);
            writer.flush();
            if (name != null) {
                writer.println(this.name);

            }
            String isAssetsIsPresent = !assets.isEmpty() ? "yes" : "no";
            writer.println(isAssetsIsPresent);
            writer.flush();
            if (!assets.isEmpty()) {
                for (int i = 0; i < assets.size(); i++) {
                    writer.println(assets.get(i).getName());
                    writer.println(assets.get(i).getPrice());
                }

            }

            writer.close();

        }


        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isNamePresent = reader.readLine();
            if (isNamePresent.equals("yes")) {
                this.name = reader.readLine();
            }
            System.out.println(name);
            String isAssetsIsPresent = reader.readLine();
            if (isAssetsIsPresent.equals("yes")){
                while (reader.ready()){
                    String assetName = reader.readLine();
                    String assetPrice = reader.readLine();
                    this.assets.add(new Asset(assetName, Double.parseDouble(assetPrice)));
                }
            }


            for (Asset a : assets){
                System.out.println(a.toString());
            }


            reader.close();


        }
    }
}
