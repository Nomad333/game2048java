package lab4.task1;

import lab4.task1.factory.Factory;
import lab4.task1.factory.RandomProductFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    /*
        Завдання 1. Описати клас для зберігання даних про тип об’єктів у
    заданій предметній області. При створенні класу дотримуватися принципу
    інкапсуляції – усі поля з даними повинні бути закритими, а для роботи з
    даними, що містяться у цих полях, реалізувати відповідні методи. Для
    створення екземплярів класу реалізувати відкриті конструктори, що
    заповнюють поля об’єктів даними. Реалізувати перевантаження одного – двох
    методів класу та конструктора класу. Для демонстрації функціонування класу
    створити масив з 5-7 екземплярів класу. Для генерування характеристик класу
    створити масиви можливих константних наборів даних та утілітний клас для
    випадкового вибору значення характеристик екземпляру та масиву екземплярів.

    Для створеного масиву реалізувати:
    а) вивід на консоль даних з усіх елементів масиву;
    б) вивід даних лише тих елементів, які відповідають заданому у варіанті
    завдання критерію;
    в) пошук в масиві та вивід на консоль об’єктів з вказаною властивістю.

    4. Предметна область: інтернет-магазин, клас: товар, орієнтовний перелік
    полів: назва товару, категорія, опис товару, ціна за одиницю, наявність на
    складі, кількість, дата поставки. Вивести окремо список відсутніх товарів.
    Реалізувати пошук товарів за вказаною категорією.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 8 масивів
        Factory<Product> factory = new RandomProductFactory();
        List<Product> products = new ArrayList<>(8);

        String path = "./product.bin";
        new Product().writeToFile(path);
        Product productFromFile = new Product().readFromFile(path);

        products.add(productFromFile);

        for (int i = 0; i < 8; i++) {
                products.add(factory.create());
        }

        System.out.println("___Вивід на консоль даних з усіх елементів масиву___");
        for (var el : products) {
            System.out.println(el);
        }
        System.out.println("___вивід даних лише тих елементів, які відповідають заданому у варіанті" +
                "    завдання критерію___");
        System.out.println(filter(products, Product::isAvailable));
        System.out.println("___пошук в масиві та вивід на консоль об’єктів з вказаною властивістю.___");
        System.out.println(filter(products, pr -> pr.getCategory() == ProductCategory.HOME));
    }

    public static List<Product> filter(List<Product> productList, Predicate<Product> pred) {
        return productList.stream().filter(pred).toList();
    }
}
