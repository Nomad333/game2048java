package lab2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    /*
    Завдання 3. Перевірте, чи надійно складений пароль. Пароль вважається надійним,
    якщо він складається з 8 або більше символів. Для створення паролю використовувати
    великі та малі англійські літери, цифри та символи !*_. Пароль має містити хоча б
    одному символу з перелічених допустимих типів символів.
     */
    public static void main(String[] args) {
        System.out.println(validatePassword("Password1!"));
        System.out.println(validatePassword("Password1"));
    }

    public static boolean validatePassword(String input) {
        Pattern pattern = Pattern.compile(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!*_.])[A-Za-z\\d!*_.]{8,}$"
        );
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
