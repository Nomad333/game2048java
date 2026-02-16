package com.game2048.cell;


//┏━━━┓
//┃ x ┃
//┗━━━┛

public class IntCell extends TextCell {
    private int number;

    public IntCell(int number) {
        super(number + "");
        this.number = number;
    }

    public IntCell(int number, int textWidth) {
        super(number + "", textWidth);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number, boolean autoWidth) {
        this.number = number;
        setText(number + "", autoWidth);
    }
}
