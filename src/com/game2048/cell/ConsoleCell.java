package com.game2048.cell;


import com.game2048.consoleobj.ConsoleObject;

import java.security.InvalidParameterException;

//┏━━━┓
//┃ x ┃
//┗━━━┛

public class ConsoleCell extends ConsoleObject {
    private int number;
    private int numWidth;

    public ConsoleCell(int number) {
        super();
        this.number = number;
        setNumWidth((number + "").length());
    }

    public ConsoleCell(int number, int numWidth) {
        super();
        this.number = number;
        setNumWidth(numWidth);
    }

    public int getNumWidth() {
        return numWidth;
    }

    public void setNumWidth(int numWidth) {
        if ((number + "").length() > numWidth) throw new InvalidParameterException("numWidth");
        this.numWidth = numWidth;
        reDraw();
    }

    public void setNumber(int number, boolean autoWidth) {
        this.number = number;
        if (autoWidth) setNumWidth((number + "").length());
        reDraw();
    }

    public int getNumber() {
        return number;
    }

    private void reDraw() {
        header = "┏━━" + "━".repeat(numWidth) + "┓";
        body = "┃ %d ".formatted(number) + " ".repeat(numWidth - (number + "").length()) + "┃";
        footer = "┗━━" + "━".repeat(numWidth) + "┛";
    }
}
