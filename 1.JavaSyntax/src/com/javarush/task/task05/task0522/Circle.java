package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
*/

public class Circle {
    public double x;
    public double y;
    public double radius;

    public double defY = 5;
    public double defRadius = 1;

    public Circle(double x) {
        this.x = x;
        this.y = defY;
        this.radius = defRadius;
    }

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = defRadius;
    }

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(){
        this(5.0, 5.0, 1.0);
    }

    public static void main(String[] args) {

    }
}