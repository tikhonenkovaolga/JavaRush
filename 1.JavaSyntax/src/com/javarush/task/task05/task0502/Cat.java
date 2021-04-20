package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public void setAge(){
        this.age = age;
    }

    public void setWeight(){
        this.weight = weight;
    }

    public void setStrength(){
        this.strength = strength;
    }

    public int getAge(int age){
        return age;
    }

    public int getWeight(int weight){
        return weight;
    }

    public int getStrength(int strength){
        return strength;
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
        if (this.age > anotherCat.age & this.weight > anotherCat.weight & this.strength > anotherCat.strength) {
            return true;
        }
        else  {
                return false;
            }
        }

    public static void main(String[] args) {

        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        cat1.fight(cat2);


    }
}
