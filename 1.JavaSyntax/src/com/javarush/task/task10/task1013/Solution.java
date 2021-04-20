package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private static String firstName;
        private static String secondName;
        private static int age;
        private static boolean sex;
        private static String address;
        private static int weight;

        public Human() {

        }

        public Human(String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
        }

        public Human(String firstName, String secondName, int age) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
        }

        public Human(String firstName, String secondName, int age, boolean sex) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
            this.sex = sex;
        }

        public Human(String firstName, String secondName, int age, String address) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
            this.address = address;
        }

        public Human(String firstName, String secondName, int age, boolean sex, int weight) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
            this.sex = sex;
            this.weight = weight;
        }

        public Human(String firstName, String secondName, int age, boolean sex, int weight, String address) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
            this.sex = sex;
            this.weight = weight;
            this.address = address;
        }

        public Human(String firstName, boolean sex, int weight) {
            this.firstName = firstName;
            this.sex = sex;
            this.weight = weight;
        }

        public Human(String firstName, String secondName, int age, int weight, String address) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.age = age;
            this.weight = weight;
            this.address = address;
        }
        public Human(String secondName, int age, boolean sex, String address) {
            this.secondName = secondName;
            this.age = age;
            this.sex = sex;
            this.address = address;
        }

    }
}
