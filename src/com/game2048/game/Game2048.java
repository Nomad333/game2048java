package com.game2048.game;

import com.game2048.color.ConsoleColor;
import com.game2048.consoleobj.IntCell;
import com.game2048.consoleobj.TextCell;
import com.game2048.printer.GridPrinter;
import com.game2048.printer.Printer;
import com.game2048.printer.RainbowGridPrinter;

import java.util.ArrayList;
import java.util.List;

public class Game2048 implements Game {
    private int oldScore = 0;
    private int currentScore = 0;
    private List<IntCell> intCells = new ArrayList<>();
    private List<TextCell> textCells = new ArrayList<>();
    private int rows, cols;
    private Printer gridPrinter;
    private Printer textPrinter;
    private boolean isMergedFlag;

    public Game2048(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows * cols; i++) {
            int[] range = {0, 2};
            intCells.add(new IntCell(range[i % (range.length)]));
        }

        textCells.add(new TextCell("Best Score: " + oldScore));
        textCells.add(new TextCell("Current Score: " + currentScore));

        textPrinter = new GridPrinter<>(textCells, ConsoleColor.BLUE);
        gridPrinter = new RainbowGridPrinter(intCells);
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

    private void calculateScore() {
        currentScore = intCells.stream().map(IntCell::getNumber).mapToInt(Integer::intValue).sum();
        if (currentScore > oldScore) oldScore = currentScore;
    }


    @Override
    public void print() {
        calculateScore();
        autoWidth();
        textCells.get(0).setText("Best Score: " + oldScore, true);
        textCells.get(1).setText("Current Score: " + currentScore, true);

        textPrinter.print();
        gridPrinter.print();
    }

    @Override
    public void moveUp() {
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < rows - 1; i++) {
                leftMerge(getRow(i), getRow(i + 1));
            }
        }

        if (isMergedFlag) randomSpawn();
        isMergedFlag = false;
    }

    @Override
    public void moveDown() {
        for (int j = 0; j < rows; j++) {
            for (int i = rows - 1; i > 0; i--) {
                leftMerge(getRow(i), getRow(i - 1));
            }
        }

        if (isMergedFlag) randomSpawn();
        isMergedFlag = false;
    }

    @Override
    public void moveLeft() {
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < cols - 1; i++) {
                leftMerge(getColumn(i), getColumn(i + 1));
            }
        }

        if (isMergedFlag) randomSpawn();
        isMergedFlag = false;
    }

    @Override
    public void moveRight() {
        for (int j = 0; j < cols; j++) {
            for (int i = cols - 1; i > 0; i--) {
                leftMerge(getColumn(i), getColumn(i - 1));
            }
        }

        if (isMergedFlag) randomSpawn();
        isMergedFlag = false;
    }

    @Override
    public void newGame(int rows, int cols) {
        currentScore = 0;
        this.rows = rows;
        this.cols = cols;

        intCells = new ArrayList<>();
        textCells = new ArrayList<>();

        for (int i = 0; i < rows * cols; i++) {
            int[] range = {0, 2, 4};
            intCells.add(new IntCell(range[i % (range.length)]));
        }

        textCells.add(new TextCell("Best Score: " + oldScore));
        textCells.add(new TextCell("Current Score: " + currentScore));

        textPrinter = new GridPrinter<>(textCells, ConsoleColor.BLUE);
        gridPrinter = new RainbowGridPrinter(intCells);
    }

    private List<IntCell> getRow(int r) {
        List<IntCell> row = new ArrayList<>();
        for (int c = 0; c < cols; c++) {
            row.add(intCells.get(r * cols + c));
        }
        return row;
    }

    private List<IntCell> getColumn(int c) {
        List<IntCell> column = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            column.add(intCells.get(r * cols + c));
        }
        return column;
    }

    private void leftMerge(List<IntCell> left, List<IntCell> right) {
        for (int i = 0; i < left.size(); i++) {
            var leftEl = left.get(i);
            var rightEl = right.get(i);
            if (leftEl.getNumber() == rightEl.getNumber()) {
                leftEl.setNumber(leftEl.getNumber() * 2, true);
                rightEl.setNumber(0, true);
                isMergedFlag = true;
            } else if (leftEl.getNumber() == 0 && rightEl.getNumber() != 0) {
                leftEl.setNumber(rightEl.getNumber(), true);
                rightEl.setNumber(0, true);
                isMergedFlag = true;
            }
        }
    }

    private void randomSpawn() {
        var emptyCells = intCells.stream().filter(el -> el.getNumber() == 0).toList();
        if (emptyCells.isEmpty()) return; // Важно!

        int randomIndex = (int) (Math.random() * emptyCells.size());
        int value = (Math.random() < 0.9) ? 2 : 4;
        emptyCells.get(randomIndex).setNumber(value, true);
    }


    @Override
    public boolean isGameEnded() {
        // 1. Если есть хотя бы один ноль — игра продолжается
        if (intCells.stream().anyMatch(cell -> cell.getNumber() == 0)) {
            return false;
        }

        // 2. Проверяем возможность мерджа по горизонтали
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols - 1; c++) {
                int current = intCells.get(r * cols + c).getNumber();
                int next = intCells.get(r * cols + (c + 1)).getNumber();
                if (current == next) return false;
            }
        }

        // 3. Проверяем возможность мерджа по вертикали
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows - 1; r++) {
                int current = intCells.get(r * cols + c).getNumber();
                int next = intCells.get((r + 1) * cols + c).getNumber();
                if (current == next) return false;
            }
        }

        return true;
    }

    @Override
    public int getOldScore() {
        return oldScore;
    }

    @Override
    public int getScore() {
        return currentScore;
    }
}
