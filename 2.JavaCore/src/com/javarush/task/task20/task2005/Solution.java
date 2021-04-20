package com.javarush.task.task20.task2005;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Очень странные дела
*/

public class Solution {
    public static void main(String[] args) {
        //исправь outputStream/inputStream в соответствии с путем к твоему реальному файлу
        try {
            File your_file_name = File.createTempFile("C://Users//Оля//Desktop//test.txt", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if (ivanov.hashCode() == somePerson.hashCode()){
                System.out.println("==");
            }
            else System.out.println("!=");

            System.out.println(ivanov.equals(somePerson));


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
            if (this == o) return false;
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
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            String isNamePresent = name != null ? "yes" : "no";
            printWriter.println(isNamePresent);
            printWriter.flush();
            if (name != null){
                printWriter.println(this.name);
            }
            String isAssetsIsPresent = !assets.isEmpty() ? "yes" : "no";
            printWriter.println(isAssetsIsPresent);
            printWriter.flush();
            if (!assets.isEmpty()) {
                for (int i = 0; i < assets.size(); i++){
                    printWriter.println(assets.get(i).getName());
                }

            }

            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isNamePresent = reader.readLine();
            if (isNamePresent.equals("yes")) {
                this.name = reader.readLine();
            }
            System.out.println(name);
            String isAssetsIsPresent = reader.readLine();
            if (isAssetsIsPresent.equals("yes")) {

                while (reader.ready()){
                    String assetName = reader.readLine();
                    this.assets.add(new Asset(assetName));
                }

                reader.close();
            }
            for (Asset a : assets){
                System.out.println(a.toString());
            }
        }
    }
}
