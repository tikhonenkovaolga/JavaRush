package com.javarush.task.task35.task3507;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws IOException {
        Set<Animal> animals = new HashSet<>();
        File[] files = new File(pathToAnimals).listFiles();

        for (File f : files) {
            try {
                if (f.isFile() & f.getName().contains(".class")) {
                    MyClassLoader myClassLoader = new MyClassLoader();
                    Class<?> clazz = myClassLoader.loadClass(f.getAbsolutePath());
                    if (Animal.class.isAssignableFrom(clazz)) {
                        Constructor<?> constructor = clazz.getConstructor();
                        animals.add((Animal) constructor.newInstance());
                    }
                }
            }
            catch (Exception e){

            }
        }

        return animals;
    }

    public static class MyClassLoader extends java.lang.ClassLoader {

        Class clazz = null;

        @Override
        public  Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(name));
                clazz = defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return clazz;
        }
    }


}
