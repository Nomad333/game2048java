package com.game2048.main;

import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;
import com.game2048.game.Game;
import com.game2048.game.Game2048;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game2048 = new Game2048(2, 2);
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        do {
            game2048.print();
            if (game2048.isGameEnded()) {
                System.out.println(StringWrapper.str("Game ended", ConsoleColor.RED_BOLD));
            }
            System.out.println("A/W/S/D - move cells, R - new game, E - exit");
            var nextCommand = scanner.nextLine().toUpperCase(Locale.ROOT);
            switch (nextCommand) {
                case "A":
                    game2048.moveLeft();
                    break;
                case "W":
                    game2048.moveUp();
                    break;
                case "S":
                    game2048.moveDown();
                    break;
                case "D":
                    game2048.moveRight();
                    break;
                case "R":
                    game2048.newGame(4, 4);
                    break;
                case "E":
                    end = true;
                    break;
                default:
                    System.out.println("invalid command");
                    Thread.sleep(500);
            }

        } while (!end);
    }
}
