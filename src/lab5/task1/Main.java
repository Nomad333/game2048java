package lab5.task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
    Завдання1. Користувач вводить з клавіатури шляхи до двох файлів.
    Програма повинна перевірити чи збігаються їх рядки.
    Якщо ні, то вивести рядки, що не збігаються із кожного з файлів
     */
    public static void main(String[] args) {
        System.out.println("Введіть шлях до першого файлу:");
        File file1;
        do {
            file1 = getFileFromConsole();
            if (file1 != null) {
                break;
            }
        } while (true);
        System.out.println("Введіть шлях до другого файлу:");
        File file2;
        do {
            file2 = getFileFromConsole();
            if (file2 != null) {
                break;
            }
        } while (true);

        compareFiles(file1, file2);
    }

    static File getFileFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String path1 = scanner.nextLine();

        File file1 = new File(path1);
        if (!file1.exists()) {
            System.out.println("Файл не існує: " + path1);
            return null;
        }
        return file1;
    }

    static void compareFiles(File file1, File file2) {
        try {
            List<String> lines1 = readFileLines(file1);
            List<String> lines2 = readFileLines(file2);

            if (lines1.equals(lines2)) {
                System.out.println("Рядки збігаються.");
            } else {
                System.out.println("Рядки не збігаються.");
                System.out.println("Рядки з першого файлу, що не збігаються:");
                for (String line : lines1) {
                    if (!lines2.contains(line)) {
                        System.out.println(line);
                    }
                }
                System.out.println("Рядки з другого файлу, що не збігаються:");
                for (String line : lines2) {
                    if (!lines1.contains(line)) {
                        System.out.println(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }

    }

    static List<String> readFileLines(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(fis);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
        return lines;
    }
}
