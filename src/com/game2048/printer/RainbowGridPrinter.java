package com.game2048.printer;

import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;
import com.game2048.consoleobj.IntCell;

import java.util.List;

public class RainbowGridPrinter extends GridPrinter<IntCell> {
    public RainbowGridPrinter(List<? extends IntCell> objects) {
        super(objects);
    }

    public RainbowGridPrinter(List<? extends IntCell> objects, ConsoleColor color) {
        super(objects, color);
    }

    public RainbowGridPrinter(List<? extends IntCell> objects, int rows, int cols, ConsoleColor color) {
        super(objects, rows, cols, color);
    }

    @Override
    public void print() {
        for (int r = 0; r < rows; r++) {
            // Вычисляем диапазон объектов для текущей строки сетки
            int startIdx = r * cols;
            int endIdx = Math.min(startIdx + cols, objects.size());

            // i — это "построчная" отрисовка внутренностей объекта (высота 3 строки)
            for (int i = 0; i < 3; i++) {
                for (int j = startIdx; j < endIdx; j++) {
                    // Берем j-й объект и печатаем его i-ю строчку
                    var maxColorIndex = ConsoleColor.values().length;
                    var hashNumber = ((Integer) objects.get(j).getNumber()).hashCode();

                    System.out.print(
                            StringWrapper.str(objects.get(j).next(),
                                    ConsoleColor.values()[hashNumber % maxColorIndex])
                    );
                }
                // Переход на новую строку консоли после того, как отрисовали i-й срез всех объектов в ряду
                System.out.println();
            }
        }
    }
}
