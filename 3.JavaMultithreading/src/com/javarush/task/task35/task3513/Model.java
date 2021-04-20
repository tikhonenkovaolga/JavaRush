package com.javarush.task.task35.task3513;


import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        score = 0;
        maxTile = 0;
        resetGameTiles();

    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (gameTiles[y][x].value == 0) {
                    emptyTiles.add(gameTiles[y][x]);
                }
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
           Tile tile = emptyTiles.get((int) (emptyTiles.size() * Math.random()));
           tile.value = (Math.random() < 0.9 ? 2:4);

        }

    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                gameTiles[y][x] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {

        boolean changes = false;

        for (int x = 0; x < FIELD_WIDTH -1; x++) {
            if (tiles[x].value == 0) {
                for (int y = x + 1; y < FIELD_WIDTH; y++) {
                    if (tiles[y].value != 0){
                        tiles[x].value = tiles[y].value;
                        tiles[y].value = 0;
                        changes = true;
                        break;
                    }
                }

            }
        }



        return changes;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changes = false;

        for (int x = 0; x < FIELD_WIDTH - 1; x++) {
            if (tiles[x].value > 0 & tiles[x].value == tiles[x + 1].value) {
                tiles[x].value = tiles[x].value * 2;
                tiles[x + 1].value = 0;
                if (maxTile < tiles[x].value) {
                    maxTile = tiles[x].value;
                }
                score += tiles[x].value;
                changes = true;
            }
        }
        compressTiles(tiles);

        return changes;

    }

    public void left() {

        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {

            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;

            }

        }
        if (isChanged) {
            addTile();
        }
        isSaveNeeded = true;
    }

    private void rotateArray() {
        Tile[][] tmp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tmp[i][j] = gameTiles[FIELD_WIDTH - 1 - j][i];
            }
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = tmp[i][j];
            }
        }

    }

    public void up() {
        saveState(gameTiles);
        rotateArray();
        rotateArray();
        rotateArray();
        left();
        rotateArray();

    }

    public void down() {
        saveState(gameTiles);
        rotateArray();
        left();
        rotateArray();
        rotateArray();
        rotateArray();

    }

    public void right() {
        saveState(gameTiles);
        rotateArray();
        rotateArray();
        left();
        rotateArray();
        rotateArray();

    }

    public boolean canMove() {
        boolean can = false;
        Tile[]tiles = new Tile[gameTiles.length];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tiles[i] = gameTiles[i][j];
            }
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) {
                    can = true;
                }
                else {

                    if (compressTiles(tiles) | mergeTiles(tiles)){
                        can = true;
                    }

                }

            }
        }

        return can;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tmp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                tmp[y][x] = new Tile(tiles[y][x].value) ;
            }
        }

        previousStates.push(tmp);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() & !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }

    }

    void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;

        }
    }

    private boolean hasBoardChanged(){
        boolean result = false;

        Tile[][]tmp = previousStates.peek();   //Чтобы узнать, кто теперь последний в стеке, не удаляя его оттуда, нужно вызвать метод peek()
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (tmp[y][x].value != gameTiles[y][x].value){
                    result = true;
                }
            }
        }

        return result;
    }

    private MoveEfficiency getMoveEfficiency(Move move){

        move.move();
        MoveEfficiency moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);

        if (!hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
       return moveEfficiency;

    }

    void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));

        priorityQueue.peek().getMove().move();


    }



}
