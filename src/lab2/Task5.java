package lab2;

public class Task5 {
    /*
    Завдання 5. Напишіть метод, який з деякого рядка,
    що містить певну сукупність електронних адрес видаляє усі адреси з доменом .ru
     */
    public static void main(String[] args) {
        String text = "test@mail.ru example@gmail.com user@yandex.ru admin@ukr.net";

        String result = removeRuEmails(text);

        System.out.println(result);
    }

    public static String removeRuEmails(String text) {
        return text.replaceAll(
                "\\b\\S+@\\S+\\.ru\\b",
                ""
        ).replaceAll("\\s{2,}", " ").trim();
    }
}
