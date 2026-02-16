package com.game2048.main;

import com.game2048.cell.ConsoleCell;
import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ConsoleCell> cells = List.of(
                new ConsoleCell(5),
                new ConsoleCell(5),
                new ConsoleCell(5),
                new ConsoleCell(5)
        );

        cells.get(0).setNumWidth(50);
        cells.get(0).setNumber(1000000, false);
        cells.get(1).setNumWidth(12);

        for (int i = 0; i < 3; i++) {
            for (var el : cells) {
                System.out.print(StringWrapper.str(el.next(), ConsoleColor.values()[i + 5 % 3]));
            }
            System.out.println();
        }
        //System.out.println(StringWrapper.str( cell.draw() +"\n" + cell.draw()+"\n" + cell.draw(), ConsoleColor.GREEN));
    }
}
