package com.game2048.main;

import com.game2048.cell.ConsoleCell;
import com.game2048.color.StringWrapper;
import com.game2048.color.ConsoleColor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleCell cell = new ConsoleCell(50000);
        List<ConsoleCell> cells = List.of(
                new ConsoleCell(5),
                new ConsoleCell(5),
        new ConsoleCell(5),
                new ConsoleCell(5)
        );

        cells.get(0).setNumSize(5);
        cells.get(1).setNumSize(50);

        for (int i = 0; i < 3; i++){
            for (var el : cells) {
                System.out.print(StringWrapper.str(el.next(), ConsoleColor.BLUE));
            }
            System.out.println();
        }
        //System.out.println(StringWrapper.str( cell.draw() +"\n" + cell.draw()+"\n" + cell.draw(), ConsoleColor.GREEN));
    }
}
