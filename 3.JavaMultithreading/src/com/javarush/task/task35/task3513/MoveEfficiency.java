package com.javarush.task.task35.task3513;

import java.util.Comparator;

public class MoveEfficiency implements Comparable<MoveEfficiency>{

    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public int getNumberOfEmptyTiles() {
        return numberOfEmptyTiles;
    }

    public int getScore() {
        return score;
    }

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        return Comparator.comparing(MoveEfficiency :: getNumberOfEmptyTiles).thenComparing(MoveEfficiency :: getScore).compare(this, o);

    }
}
