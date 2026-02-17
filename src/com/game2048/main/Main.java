package com.game2048.main;

import com.game2048.game.Game2048;

public class Main {
    public static void main(String[] args) {
        Game2048 game2048 = new Game2048(6, 6);
        game2048.print();
    }
}
