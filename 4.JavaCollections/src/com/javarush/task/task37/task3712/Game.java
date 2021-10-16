package com.javarush.task.task37.task3712;

public abstract class Game {
    private Game game;

    public abstract void prepareForTheGame();

    public abstract void playGame();

    public abstract void congratulateWinner();

    public void run(){
       prepareForTheGame();
       playGame();
       congratulateWinner();
    }
}
