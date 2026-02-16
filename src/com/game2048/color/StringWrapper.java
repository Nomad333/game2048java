package com.game2048.color;

public class StringWrapper {
    public static String str(String text, ConsoleColor color){
        return color+text+ConsoleColor.RESET;
    }
}
