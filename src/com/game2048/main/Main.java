package com.game2048.main;

import com.game2048.cell.IntCell;
import com.game2048.cell.TextCell;
import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TextCell> cells = List.of(
                new TextCell("Text"),
                new TextCell("TEXT"),
                new TextCell("TEST"),
                new TextCell("COUNT:5"),
                new IntCell(5)
        );

        for (int i = 0; i < 3; i++) {
            for (var el : cells) {
                System.out.print(StringWrapper.str(el.next(), ConsoleColor.values()[i + 5 % 3]));
            }
            System.out.println();
        }
        //System.out.println(StringWrapper.str( cell.draw() +"\n" + cell.draw()+"\n" + cell.draw(), ConsoleColor.GREEN));
    }
}
