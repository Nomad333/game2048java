package com.game2048.printer;

import com.game2048.color.ConsoleColor;
import com.game2048.color.StringWrapper;
import com.game2048.consoleobj.ConsoleObject;

import java.util.ArrayList;
import java.util.List;

public class GridPrinter<T extends ConsoleObject> implements Printer {
    private List<? extends T> objects = new ArrayList<>();
    private int rows;
    private int cols;
    private ConsoleColor consoleColor;

    public GridPrinter(List<? extends T> objects) {
        this(objects, ConsoleColor.RESET);
    }

    public GridPrinter(List<? extends T> objects, ConsoleColor color) {
        this(objects, 0, 0, color);
        int size = objects.size();
        if (size == 0) {
            this.rows = 0;
            this.cols = 0;
            return;
        }

        this.cols = (int) Math.ceil(Math.sqrt(size));
        this.rows = Math.ceilDiv(size, this.cols);
    }


    public GridPrinter(List<? extends T> objects, int rows, int cols, ConsoleColor color) {
        this.consoleColor = color;
        this.objects = objects;
        this.rows = rows;
        this.cols = cols;
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
                    System.out.print(StringWrapper.str(objects.get(j).next(), consoleColor));
                }
                // Переход на новую строку консоли после того, как отрисовали i-й срез всех объектов в ряду
                System.out.println();
            }
        }
    }

    public List<? extends T> getObjects() {
        return objects;
    }

    public void setObjects(List<? extends T> objects) {
        this.objects = objects;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public ConsoleColor getConsoleColor() {
        return consoleColor;
    }

    public void setConsoleColor(ConsoleColor consoleColor) {
        this.consoleColor = consoleColor;
    }
}
