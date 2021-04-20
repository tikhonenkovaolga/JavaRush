package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Solution {
    public static void main(String[] args) {

        Human child1 = new Human("Sam", true, 8, new ArrayList<>());
        System.out.println(child1.toString());

        Human child2 = new Human("Fox", false, 6, new ArrayList<>());
        System.out.println(child2.toString());

        Human child3 = new Human("Red", false, 3, new ArrayList<>());
        System.out.println(child3.toString());

        Human father = new Human("Bob", true, 35, new ArrayList<>(Arrays.asList(child1, child2, child3)));
        System.out.println(father.toString());

        Human mother = new Human("Suzy", false, 33, new ArrayList<>(Arrays.asList(child1, child2, child3)));
        System.out.println(mother.toString());

        Human grandFather1 = new Human("Alex", true, 65, new ArrayList<Human>(Arrays.asList(father)));
        System.out.println(grandFather1.toString());

        Human grandFather2 = new Human("Paul", true, 70, new ArrayList<Human>(Arrays.asList(mother)));
        System.out.println(grandFather2.toString());

        Human grandMother1 = new Human("Ann", false, 60, new ArrayList<Human>(Arrays.asList(father)));
        System.out.println(grandMother1.toString());

        Human grandMother2 = new Human("Donna", false,  68, new ArrayList<Human>(Arrays.asList(mother)));
        System.out.println(grandMother2.toString());

    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human(String name, boolean sex, int age, ArrayList<Human>children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;

        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
