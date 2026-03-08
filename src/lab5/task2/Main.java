package lab5.task2;

import java.util.Scanner;

public class Main {
    /*
    Завдання 2 Користувач отримує шлях до каталогу з текстовими файлами,
    які необхідно перевірити на наявність заборонених слів. Перелік заборонених
    слів необхідно отримати з файлу, що розташований у корені проекту. Користувачу
    на екран потрібно вивести детальну інформацію про файли, що містять заборонені
    слова (які заборонені слова та їх кількість). Далі необхідно запропонувати користувачу
    виправити файли шляхом заміни символів заборонених слів символом ‘*’ (зірочка).
    Цю пропозицію потрібно пропонувати для кожного файлу окремо та виправляти файл
    лише за згодою користувача.
     */
    public static void main(String[] args) {
        var dirPath = "lab5-2/DirectoryForCheck";
        var banWordsFilePath = "lab5-2/banwords.txt";
        var checker = new BanWordFileChecker(dirPath, banWordsFilePath);
        dialog(checker);

        checker.printInfoAboutBanWordsInFiles();
    }

    public static void dialog(BanWordFileChecker checker) {

        for (var entry : checker.getBanWordsInFiles().entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println("File: " + entry.getKey().getName());
                for (var wordEntry : entry.getValue().entrySet()) {
                    System.out.println("  Ban word: " + wordEntry.getKey() + ", Count: " + wordEntry.getValue());
                }
                System.out.println("Do you want to replace ban words in this file? (y/n)");
                if (getAnswerFromConsole()) {
                    checker.replaceBanWordsInFile(entry.getKey());
                    System.out.println("Replaced");
                }
            }
        }
    }

    public static boolean getAnswerFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("y");
    }
}
