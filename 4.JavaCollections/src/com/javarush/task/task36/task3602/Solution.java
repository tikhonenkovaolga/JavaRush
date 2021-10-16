package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class [] clazz = Collections.class.getDeclaredClasses();
        for (Class c : clazz) {
            if (List.class.isAssignableFrom(c) & List.class.isAssignableFrom(c.getSuperclass())){
                if ((Modifier.isPrivate(c.getModifiers())) & (Modifier.isStatic(c.getModifiers()))){
                    try{
                        Method method = c.getDeclaredMethod("get", int.class);
                        method.setAccessible(true);
                        Constructor<?> constructor = c.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        method.invoke(constructor.newInstance(), 1);

                    }
                    catch (NoSuchMethodException | InstantiationException | IllegalAccessException e){

                    }
                    catch (InvocationTargetException e){
                        if (e.getCause().toString().contains("IndexOutOfBoundsException"))return c;
                    }

                }
            }

        }

        return null;
    }
}
