package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        Human alex = new Human("Alex", true, 55);
        Human zac = new Human("Zac", true, 80);
        Human paul = new Human("Paul", true, 60);
        Human ann = new Human("Ann", false, 90);
        Human john = new Human("John", true, 25, alex, ann);
        Human sansa = new Human("Sansa", false, 22, alex, ann);
        Human bran = new Human("Bran", true, 18, zac, ann);
        Human aria = new Human("Aria", false, 17, zac, ann);
        Human deni = new Human("Deni", false, 19, paul, ann);
        System.out.println(alex.toString());
        System.out.println(zac.toString());
        System.out.println(paul.toString());
        System.out.println(ann.toString());
        System.out.println(john.toString());
        System.out.println(sansa.toString());
        System.out.println(bran.toString());
        System.out.println(aria.toString());
        System.out.println(deni.toString());

    }
    public static class Human {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;
        public Human(){
        }
        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }
        public Human(String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }
        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }
}

//1. Программа не должна считывать данные с клавиатуры.
//        2. Добавить в класс Human поля: имя(String), пол(boolean), возраст(int), отец(Human), мать(Human).
//        3. Добавить в класс конструктор public Human(String name, boolean sex, int age).
//        4. Добавить в класс конструктор public Human(String name, boolean sex, int age, Human father, Human mother).
//        5. Создай 9 разных объектов типа Human (4 объекта без отца и матери и 5 объектов с ними)).
//        6. Выведи созданные объекты на экран.