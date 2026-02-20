package com.game2048.color;

public enum ConsoleColor {
    // Сброс
    RESET("\033[0m"),

    // Обычные цвета
    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m"),

    // Жирный текст
    BLACK_BOLD("\033[1;30m"),
    RED_BOLD("\033[1;31m"),
    GREEN_BOLD("\033[1;32m");

    // Фон
//    RED_BACKGROUND("\033[41m"),
//    GREEN_BACKGROUND("\033[42m"),
//    BLUE_BACKGROUND("\033[44m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}