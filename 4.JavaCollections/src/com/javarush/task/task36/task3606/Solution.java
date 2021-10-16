package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File[] files = new File(packageName).listFiles();
        for (File f: files){
            try {
                if (f.isFile() & f.getName().contains(".class")){
                    MyClassLoader classLoader = new MyClassLoader();
                    Class<?> clazz = classLoader.loadClass(f.getAbsolutePath());
                    if (HiddenClass.class.isAssignableFrom(clazz)){
                        hiddenClasses.add(clazz);
                    }

                }
            }
            catch (Exception e){

            }
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass hiddenClass = null;
        key = key.toLowerCase();
        for (Class c: hiddenClasses) {
            if (c.getSimpleName().toLowerCase().startsWith(key)){
                try {
                    Constructor constructor = c.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    hiddenClass = (HiddenClass) constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }

        }
        return hiddenClass;
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

