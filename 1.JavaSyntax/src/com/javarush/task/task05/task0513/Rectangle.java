package com.javarush.task.task05.task0513;

/* 
Собираем прямоугольник
*/

public class Rectangle {
    //напишите тут ваш код
    private int top;
    private int left;
    private int width;
    private  int height;

    private int defWidth = 0;
    private int defHeight = 0;

//    public void setTop(int top){
//        this.top = top;
//    }
//
//    public void setLeft(int left){
//        this.left = left;
//    }
//
//    public void setWidth(int width){
//        this.width = width;
//    }
//
//    public void setHeight(int height){
//        this.height = height;
//    }
//
//    public int getTop(){
//        return top;
//    }
//
//    public int getLeft(){
//        return left;
//    }
//
//    public int getWidth(){
//        return width;
//    }
//
//    public int getHeight(){
//        return height;
//    }
//

    public void initialize (int left, int top, int width, int height){
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public void initialize(int top, int left){
        this.top = top;
        this.left = left;
        this.width = defWidth;
        this.height = defHeight;
    }

    public void initialize(int top, int left, int width){
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = width;
    }

    public void initialize (Rectangle other){//создание копии конструктора
        this.top = other.top;
        this.left = other.left;
        this.width = other.width;
        this.height = other.height;
    }


    public static void main(String[] args) {

    }
}
