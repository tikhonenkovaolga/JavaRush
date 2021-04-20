package com.javarush.task.task05.task0527;

/* 
Том и Джерри
*/

public class Solution {
    public static void main(String[] args) {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
        Cat tom = new Cat("Tom", 5, "grey");
        Dog bob = new Dog("Bob", 10, "white");


    }

    public static class Mouse {
        String name;
        int height;
        int tail;

        public Mouse(String name, int height, int tail) {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    public static class Cat{
        String name;
        int age;
        String color;

        public Cat(String name, int age, String color){
            this.name = name;
            this.age = age;
            this.color = color;
        }
    }

    public static class Dog{
        String name;
        int age;
        String color;

        public Dog(String name, int age, String color){
            this.name = name;
            this.age = age;
            this.color = color;
        }

    }
}
