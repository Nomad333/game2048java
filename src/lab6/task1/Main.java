package lab6.task1;

import java.util.Scanner;

public class Main {
    /*
    Завдання1. Необхідно  розробити програму, яка дозволить
    зберігати інформацію про авторизаціє користувачів. Кожному
    користувачеві відповідає пара  логін  та пароль. При старті
    програма відображає меню:
    Додати нового користувача;
    Видалити існуючого користувача;
    Перевірити чи існує користувач;
    Змінити  логін існуючого користувача;
    Змінити пароль користувача;

     */
    public static void main(String[] args) {
        UsersManager usersManager = new UsersManager();
        usersManager.addUser(new User("user1", "password1"));
        dialog(usersManager);
    }

    public static void dialog(UsersManager usersManager) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1. Додати нового користувача
                    2. Видалити існуючого користувача
                    3. Перевірити чи існує користувач
                    4. Змінити  логін існуючого користувача
                    5. Змінити пароль користувача
                    6. Показати всіх
                    0. Вихід
                    """);

            int cmd = scanner.nextInt();
            scanner.nextLine();

            switch (cmd) {
                case 1 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    usersManager.addUser(new User(login, password));
                }
                case 2 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();

                    usersManager.removeUser(u -> u.getLogin().equals(login));
                }
                case 3 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();

                    System.out.println(usersManager.isUserExists(u -> u.getLogin().equals(login))
                            ? "User exists" : "User not found");
                }
                case 4 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();

                    System.out.print("New login: ");
                    String newLogin = scanner.nextLine();

                    usersManager.changeLogin((u -> u.getLogin().equals(login)), newLogin);
                }
                case 5 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();

                    System.out.print("New password: ");
                    String newPassword = scanner.nextLine();

                    usersManager.changePassword((u -> u.getLogin().equals(login)), newPassword);
                }
                case 6 ->
                        usersManager.getUsers().forEach(u -> System.out.println("Login: " + u.getLogin() + ", Password: " + u.getPassword()));
                case 0 -> {
                    return;
                }
            }
            System.out.println("-".repeat(20));
        }
    }
}
