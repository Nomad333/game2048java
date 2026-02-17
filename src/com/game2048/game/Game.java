package com.game2048.game;

import com.game2048.printer.Printer;

public interface Game extends Printer {
    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

    void newGame(int rows, int cols);

    boolean isGameEnded();

    int getOldScore();

    int getScore();
}
