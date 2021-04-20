package com.javarush.games.snake;
import com.javarush.engine.cell.*;


import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject{

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        super(x, y);
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x + 1, y);
        GameObject gameObject3 = new GameObject(x + 2, y);
        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);
    }

    public void setDirection(Direction direction) {

        if (direction == Direction.DOWN & this.direction != Direction.UP & snakeParts.get(0).x != snakeParts.get(1).x  || direction == Direction.UP & this.direction != Direction.DOWN &
                snakeParts.get(0).x != snakeParts.get(1).x || direction == Direction.LEFT & this.direction != Direction.RIGHT & snakeParts.get(0).y != snakeParts.get(1).y ||
                direction == Direction.RIGHT & this.direction != Direction.LEFT & snakeParts.get(0).y != snakeParts.get(1).y){
            this.direction = direction;
        }


    }

    public void draw(Game game){

        for (int i = 0; i < snakeParts.size(); i++) {
            Color color;
            if (isAlive){
                color = Color.BLACK;
            }
            else color = Color.RED;

            if (i == 0){
                game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, color, 75);

            }
            else {
               game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y,Color.NONE, BODY_SIGN, color, 75);

            }
        }
    }

    public void move(Apple apple){
        GameObject gameObject = createNewHead();
        if (gameObject.x >= SnakeGame.WIDTH || gameObject.y >= SnakeGame.HEIGHT || gameObject.x < 0 || gameObject.y < 0){
            isAlive = false;
        }
        else{
            if (checkCollision(gameObject)){
                isAlive = false;
                return;
            }
            else {
                snakeParts.add(0, gameObject);
            }

            if (snakeParts.get(0).x == apple.x & snakeParts.get(0).y == apple.y){
                apple.isAlive = false;
            }
            else removeTail();
        }

    }

    public GameObject createNewHead(){
        GameObject gameObject = null;

        switch (direction){
            case LEFT:
                gameObject = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
                break;
            case DOWN:
                gameObject = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
                break;
            case UP:
                gameObject = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
                break;
            case RIGHT:
                gameObject = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
                break;
        }


        return gameObject;
    }


    public void removeTail(){
        snakeParts.remove(snakeParts.size()-1);
    }

    public boolean checkCollision(GameObject gameObject){
        boolean result = false;

        for (int i = 0; i < snakeParts.size(); i++){
            if (gameObject.x == snakeParts.get(i).x & gameObject.y == snakeParts.get(i).y){
                result = true;
            }
        }

        return result;

    }

    public int getLength(){
        return snakeParts.size();
    }

}
