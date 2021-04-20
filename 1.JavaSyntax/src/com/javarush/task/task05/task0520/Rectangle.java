package com.javarush.task.task05.task0520;

/* 
Создать класс прямоугольник (Rectangle)
*/


public class Rectangle {
    //напишите тут ваш код
    private int top;
    private int left;
    private int width;
    private  int height;

    private int defWidth = 0;
    private int defHeight = 0;

    public Rectangle (int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }


    public Rectangle(int top, int left){
        this.top = top;
        this.left = left;
        this.width = defWidth;
        this.height = defHeight;
    }

    public Rectangle(int top, int left, int width){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = width;
    }

    public Rectangle (Rectangle other){//создание копии конструктора
        this.top = other.top;
        this.left = other.left;
        this.width = other.width;
        this.height = other.height;
    }



    public static void main(String[] args) {

    }
}
