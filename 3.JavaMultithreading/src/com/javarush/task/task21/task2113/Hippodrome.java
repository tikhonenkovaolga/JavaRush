package com.javarush.task.task21.task2113;

import java.util.*;

public class Hippodrome {
    static Hippodrome game;
    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }
    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {

    Horse fire = new Horse("fire", 3,0);
    Horse wind = new Horse("wind", 3, 0);
    Horse plotva = new Horse("plotva", 3, 0);
    List<Horse> horses1 = new ArrayList<>();
    horses1.add(fire);
    horses1.add(wind);
    horses1.add(plotva);


        game = new Hippodrome(horses1);
        game.run();

        game.printWinner();
    }
    void run() throws InterruptedException {
        for (int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);
        }

    }
    void  move(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).move();
        }

    }
    void print(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
        
    }
    Horse winner = new Horse("null", 0, 0);
    public Horse getWinner(){

        double max = horses.get(0).distance;
        for (int i = 1; i < horses.size(); i++){
           if (horses.get(i).distance > max){
               max = horses.get(i).distance;
           }

        }
        for (int i = 0; i < horses.size(); i++){
            if (horses.get(i).distance == max){
                winner = horses.get(i);
            }

        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().name + "!");
    }
}
