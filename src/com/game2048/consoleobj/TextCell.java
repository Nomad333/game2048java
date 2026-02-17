package com.game2048.consoleobj;


import java.security.InvalidParameterException;

//┏━━━┓
//┃ x ┃
//┗━━━┛

public class TextCell extends ConsoleObject {
    private String text;
    private int textWidth;

    public TextCell(String text) {
        super();
        this.text = text;
        setTextWidth(text.length());
    }

    public TextCell(String text, int textWidth) {
        super();
        this.text = text;
        setTextWidth(textWidth);
    }

    public int getTextWidth() {
        return textWidth;
    }

    public void setTextWidth(int textWidth) {
        if (text.length() > textWidth) throw new InvalidParameterException("numWidth");
        this.textWidth = textWidth;
        reDraw();
    }

    public void setText(String text, boolean autoWidth) {
        this.text = text;
        if (autoWidth) setTextWidth(text.length());
        reDraw();
    }

    public String getText() {
        return text;
    }

    private void reDraw() {
        header = "┏━━" + "━".repeat(textWidth) + "┓";
        body = "┃ %s ".formatted(text) + " ".repeat(textWidth - text.length()) + "┃";
        footer = "┗━━" + "━".repeat(textWidth) + "┛";
    }
}
