package lab2;

public class Task2 {
    /*
    Завдання 2. Для деякого об’єкту StringBuilder виконайте таке:
    отримайте підрядки з основного рядка з використанням getChars() або subString();
    додайте підрядки з можливістю додавання підрядка як у кінець так і у середину
    існуючого рядка( append(), insert());
    видаліть або замініть деякий підрядок з основного рядка (delete(), insert()).

     */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello world!");

        // 1 substring()
        String sub1 = sb.substring(0, 5);
        System.out.println("substring(): " + sub1);

        // 2 append
        sb.append(" Java");
        System.out.println("append(): " + sb);

        // 3 insert
        sb.insert(6, "beautiful ");
        System.out.println("insert(): " + sb);

        // 4 delete
        sb.delete(6, 16);
        System.out.println("delete(): " + sb);

        // 5 replace
        sb.replace(6, 11, "Java");
        System.out.println("replace(): " + sb);
    }
}
