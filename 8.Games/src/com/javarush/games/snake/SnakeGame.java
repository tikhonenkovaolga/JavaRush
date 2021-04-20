package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    private static final int GOAL = 28;
    private boolean isGameStopped;
    private Snake snake;
    private Apple apple;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private int turnDelay;
    private int score;

    @Override
    public void initialize() {
       setScreenSize(WIDTH, HEIGHT);
       createGame();
    }

    private void createGame(){
        score = 0;
        turnDelay = 300;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        setTurnTimer(turnDelay);
        setScore(score);



    }

    private void drawScene(){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.BEIGE, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive){
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (!snake.isAlive){
            gameOver();
        }
        if (snake.getLength() > GOAL){
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case SPACE:
                if (isGameStopped){
                    createGame();
                }
                break;
        }
    }

    private void createNewApple(){
        int x = getRandomNumber(WIDTH);
        int y = getRandomNumber(HEIGHT);
        apple =new Apple(x, y);
        while (snake.checkCollision(apple)){
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }


    }

    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER!", Color.BLACK, 25);
    }

    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.RED, "YOU WIN!!!", Color.WHITE, 25);
    }
}
