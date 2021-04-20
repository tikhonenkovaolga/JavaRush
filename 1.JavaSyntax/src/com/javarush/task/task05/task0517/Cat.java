package com.javarush.task.task05.task0517;

/* 
Конструируем котиков
*/

public class Cat {
    //напишите тут ваш код
    private String name;
    private int age;
    private int weight;
    private String address;
    private String color;

    private int defAge = 5;
    private int defWeight = 5;
    private String defColor = "черный";
    private String defAddress = "улица";
    private String defName = "никто";


    public Cat(String name){
        this.name = name;
        this.age = defAge;
        this.weight = defWeight;
        this.color = defColor;

    }

    public Cat(String name, int weight, int age){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = defColor;
        //System.out.println(name + age + weight);
    }

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
        this.weight = defWeight;
        this.color = defColor;

    }

    public Cat(int weight, String color){
        this.age = defAge;
        this.weight = weight;
        this.color = color;

    }

    public Cat(int weight, String color, String address) {

        this.age = defAge;
        this.weight = weight;
        this.color = color;
        this.address = address;
    }


    public static void main(String[] args) {

    }
}
