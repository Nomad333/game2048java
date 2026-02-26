package com.game2048.printer;

import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;
import com.game2048.consoleobj.IntCell;

import java.util.*;

public class RainbowGridPrinter extends GridPrinter<IntCell> {
    private final Deque<ConsoleColor> pool = new ArrayDeque<>(List.of(ConsoleColor.values()));
    private final Map<Integer, ConsoleColor> colorMap = new HashMap<>();

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
                    // Берем j-й объект и печатаем его i-юobjects.get(j).getNumber() строчку
                    var maxColorIndex = ConsoleColor.values().length;
                    ConsoleColor color;
                    var cell = objects.get(j);

                    if (colorMap.containsKey(objects.get(j).getNumber())) {
                        color = colorMap.get(cell.getNumber());
                    } else {
                        color = pool.poll();
                        color = color != null ? color : ConsoleColor.WHITE;
                        colorMap.put(cell.getNumber(), color);
                    }

                    System.out.print(
                            StringWrapper.str(objects.get(j).next(), color)
                    );
                }
                // Переход на новую строку консоли после того, как отрисовали i-й срез всех объектов в ряду
                System.out.println();
            }
        }
    }
}
