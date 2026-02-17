package com.game2048.game;

import com.game2048.color.ConsoleColor;
import com.game2048.consoleobj.IntCell;
import com.game2048.consoleobj.TextCell;
import com.game2048.printer.GridPrinter;
import com.game2048.printer.Printer;

import java.util.ArrayList;
import java.util.List;

public class Game2048 implements Game {
    private int oldScore = 0;
    private int currentScore = 0;
    private List<IntCell> intCells = new ArrayList<>();
    private List<TextCell> textCells = new ArrayList<>();
    private boolean isGameEnded;
    private int rows, cols;
    private final Printer gridPrinter;
    private final Printer textPrinter;

    public Game2048(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows * cols; i++) {
            int[] range = {0, 2, 4, 5000};
            intCells.add(new IntCell(range[i % (range.length)]));
        }

        textCells.add(new TextCell("Best Score: " + oldScore));
        textCells.add(new TextCell("Current Score: " + currentScore));

        textPrinter = new GridPrinter<>(textCells, ConsoleColor.BLUE);
        gridPrinter = new GridPrinter<>(intCells);
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void newGame(int rows, int cols) {

    }

    @Override
    public boolean isGameEnded() {
        return false;
    }

    @Override
    public int getOldScore() {
        return oldScore;
    }

    @Override
    public int getScore() {
        return currentScore;
    }

    private void autoWidth() {
        List<Integer> numbers = new ArrayList<>();
        for (var el : intCells) {
            numbers.add(el.getNumber());
        }
        int maxWidth = numbers.stream().map((el) -> el + "").max((el1, el2) -> {
            return Integer.compare(el1.length(), el2.length());
        }).orElse("1").length();

        for (var el : intCells) {
            el.setTextWidth(maxWidth);
        }
    }

    @Override
    public void print() {
        autoWidth();


        textPrinter.print();
        gridPrinter.print();
    }
}
