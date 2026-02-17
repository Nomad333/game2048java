package com.game2048.main;

import com.game2048.color.ConsoleColor;
import com.game2048.consoleobj.TextCell;
import com.game2048.printer.GridPrinter;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<TextCell> cells = List.of(
                new TextCell("Text", 15),
                new TextCell("TEXT", 15),
                new TextCell("TEST", 15),
                new TextCell("Text", 15),
                new TextCell("TEXT", 15),
                new TextCell("TEST", 15),
                new TextCell("Text", 15),
                new TextCell("Text", 10),
                new TextCell("Text", 15),
                new TextCell("Text", 15),
                new TextCell("Text", 10),
                new TextCell("Text", 15),
                new TextCell("Text", 15),
                new TextCell("Text", 15),
                new TextCell("Text", 15),
                new TextCell("Text", 15)
        );

        GridPrinter<TextCell> printer = new GridPrinter<>(cells, ConsoleColor.CYAN);
        printer.print();
    }
}
