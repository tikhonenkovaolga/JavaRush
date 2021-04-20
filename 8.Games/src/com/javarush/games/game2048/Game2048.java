package com.javarush.games.game2048;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

import java.util.ArrayList;
import java.util.List;

public class Game2048 extends Game {

    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private int max = 0;
    private boolean isGameStopped = false;
    private int score = 0;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }

    }

    private void createNewNumber() {
        if (getMaxTileValue() == 2048) {
            win();
        } else {
            while (true) {
                int x = getRandomNumber(SIDE);
                int y = getRandomNumber(SIDE);
                if (gameField[y][x] == 0) {
                    gameField[y][x] = getRandomNumber(10) < 9 ? 2 : 4;
                    break;
                }
            }
        }

    }

    private Color getColorByValue(int value) {
        Color color = null;

        switch (value) {
            case 0:
                color = Color.WHITE;
                break;

            case 2:
                color = Color.GREEN;
                break;

            case 4:
                color = Color.YELLOW;
                break;

            case 8:
                color = Color.AQUA;
                break;
            case 16:
                color = Color.ANTIQUEWHITE;
                break;
            case 32:
                color = Color.AQUAMARINE;
                break;
            case 64:
                color = Color.RED;
                break;
            case 128:
                color = Color.ORANGE;
                break;
            case 256:
                color = Color.BEIGE;
                break;
            case 512:
                color = Color.BISQUE;
                break;
            case 1024:
                color = Color.BLUEVIOLET;
                break;
            case 2048:
                color = Color.CORAL;
                break;


        }
        return color;
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color color = getColorByValue(value);
        if (value == 0) {
            setCellValueEx(x, y, color, "");
        } else {
            setCellValueEx(x, y, color, String.valueOf(value));
        }

    }

    private boolean compressRow(int[] row) {
        boolean result = false;
        int[] begin = new int[row.length];
        System.arraycopy(row, 0, begin, 0, row.length);

        List<Integer> emptyList = new ArrayList<>();
        List<Integer> foolList = new ArrayList<>();

        for (int x = 0; x < SIDE; x++) {
            if (row[x] == 0) {
                emptyList.add(row[x]);
            } else foolList.add(row[x]);
        }

        for (int i = 0; i < foolList.size(); i++) {
            row[i] = foolList.get(i);
        }

        for (int i = foolList.size(); i < row.length; i++) {
            for (int j = 0; j < emptyList.size(); j++) {
                row[i] = emptyList.get(j);
            }
        }

        for (int i = 0; i < begin.length; i++) {
            if (begin[i] != row[i]) {
                result = true;
            }
        }
        return result;
    }

    private boolean mergeRow(int[] row) {
        boolean result = false;

        for (int x = 0; x < SIDE - 1; x++) {
            if (row[x] > 0 & row[x] == row[x + 1]) {
                row[x] = row[x] * 2;
                row[x + 1] = 0;
                result = true;
                score += row[x];
                setScore(score);
            }
        }

        return result;
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped){
            if (key == Key.SPACE){
                isGameStopped = false;
                score = 0;
                setScore(score);
                createGame();
                drawScene();
            }

        }
        else {
            if (!canUserMove()){
                gameOver();
            }
            else {
                if (key == Key.LEFT) {
                    moveLeft();
                    drawScene();
                } else if (key == Key.RIGHT) {
                    moveRight();
                    drawScene();
                } else if (key == Key.UP) {
                    moveUp();
                    drawScene();
                } else if (key == Key.DOWN) {
                    moveDown();
                    drawScene();
                }
            }
        }

        
    }

    private void moveLeft() {
        boolean change = false;
        for (int x = 0; x < SIDE; x++) {

            if (compressRow(gameField[x])) change = true;
            if (mergeRow(gameField[x])) change = true;
            if (compressRow(gameField[x])) change = true;

        }
        if (change) createNewNumber();

    }


    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();

    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] tmp = new int[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                tmp[i][j] = gameField[SIDE - 1 - j][i];
            }
        }
        System.arraycopy(tmp, 0, gameField, 0, SIDE);
    }

    private int getMaxTileValue() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (max < gameField[i][j]) {
                    max = gameField[i][j];
                }

            }
        }
        return max;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.YELLOWGREEN, "YOU WIN!!!", Color.RED, 25);
    }

    private boolean canUserMove() {
        boolean can = false;

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] == 0) {
                    can = true;
                } else {
                    try {
                        if (gameField[i][j] == (gameField[i][j + 1])) {
                            can = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }
                    try {

                        if (gameField[i][j] == (gameField[i][j - 1])) {
                            can = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }
                    try {

                        if (gameField[i][j] == (gameField[i - 1][j])) {
                            can = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }
                    try {
                        if (gameField[i][j] == (gameField[i + 1][j])) {
                            can = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }
                }
            }

        }


        return can;
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.BROWN, "Game Over!!!", Color.YELLOW, 25);
    }

}
