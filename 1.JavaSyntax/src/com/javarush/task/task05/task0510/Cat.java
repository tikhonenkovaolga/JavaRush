package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
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


    public void initialize(String name) {
        this.name = name;
        this.age = defAge;
        this.weight = defWeight;
        this.color = defColor;

    }

    public void initialize(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = defColor;
       // System.out.println(name + age + weight);
    }

    public void initialize(String name, int age) {
        this.name = name;
        this.age = age;
        this.weight = defWeight;
        this.color = defColor;
        this.address = defAddress;
    }

    public void initialize(int weight, String color) {
        //this.name = defName;
        this.age = defAge;
        this.weight = weight;
        this.color = color;

    }

    public void initialize(int weight, String color, String address) {

        this.age = defAge;
        this.weight = weight;
        this.color = color;
        this.address = address;

    }
        public static void main (String[]args){

        }
    }

