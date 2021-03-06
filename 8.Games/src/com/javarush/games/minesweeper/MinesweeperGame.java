package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
            }
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {

                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }

        }
        countMineNeighbors();
        countFlags = countMinesOnField;

    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        List<GameObject> neighbors = new ArrayList<>();
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (!gameField[y][x].isMine) {
                    neighbors = getNeighbors(gameField[y][x]);
                    for (GameObject g : neighbors) {
                        if (g.isMine) {
                            gameField[y][x].countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (gameField[y][x].isOpen || gameField[y][x].isFlag || isGameStopped){
            return;
        }
        gameField[y][x].isOpen = true;
        countClosedTiles--;
        setCellColor(x, y, Color.GREEN);
        if (gameField[y][x].isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();


        }
        else if(!gameField[y][x].isMine && gameField[y][x].countMineNeighbors == 0) {
            setCellValue(x, y, "");
            score += 5;
            for (GameObject g : getNeighbors(gameField[y][x])) {
                if (!g.isOpen) {
                    openTile(g.x, g.y);
                }

            }
                if (countClosedTiles == countMinesOnField){
                    win();
                }

        }
            else if (!gameField[y][x].isMine && gameField[y][x].countMineNeighbors != 0){

            setCellNumber(x, y, (gameField[y][x]).countMineNeighbors);
            System.out.println(gameField[y][x].countMineNeighbors);
            score += 5;
            if (countClosedTiles == countMinesOnField){
                win();
            }
            }
        setScore(score);

        }


    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped){
            restart();

        }
        else {
            openTile(x, y);
        }



    }

    private void markTile(int x, int y){
        if (isGameStopped){
            return;
        }
        if (gameField[y][x].isOpen || countFlags == 0 && !gameField[y][x].isFlag  ){
            return;
        }

        if (!gameField[y][x].isFlag){
            gameField[y][x].isFlag = true;
            setCellValue(x, y, FLAG);
            countFlags--;
            setCellColor(x, y, Color.YELLOW);
        }
        else if (gameField[y][x].isFlag){
            gameField[y][x].isFlag = false;
            setCellValue(x, y, "");
            countFlags++;
            setCellColor(x, y, Color.ORANGE);
        }

    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.BROWN, "Game Over!", Color.YELLOW, 25);
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.RED, "YOU WIN!!!", Color.BEIGE, 30);
    }

    private void restart(){
        isGameStopped = false;
        countClosedTiles = SIDE*SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);

        createGame();
    }
}


