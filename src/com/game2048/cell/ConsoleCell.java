package com.game2048.cell;


import com.game2048.consoleobj.ConsoleObject;

//┏━━━┓
//┃ x ┃
//┗━━━┛

public class ConsoleCell extends ConsoleObject{
    private int number;

    public ConsoleCell(int number) {
        super();
        setNumber(number);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        int repeatCount = (number+"").length();
        header = "┏━━" + "━".repeat(repeatCount) + "┓";
        body = "┃ %d ┃".formatted(number);
        footer = "┗━━" + "━".repeat(repeatCount) + "┛";
    }
    public void setNumSize(int repeatCount) {
        header = "┏━━" + "━".repeat(repeatCount) + "┓";
        body = "┃ %d ".formatted(number) +" ".repeat(repeatCount-(number+"").length()) + "┃";
        footer = "┗━━" + "━".repeat(repeatCount) + "┛";
    }
}
