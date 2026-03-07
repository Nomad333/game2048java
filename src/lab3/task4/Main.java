package lab3.task4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = List.of(
                new Tiger("Шерхан", 8.5),
                new Tiger("Амба", 7.0),
                new Crocodile("Гєна", 3.2),
                new Kangaroo("Джек", 4.5)
        );

        ZooManager myZoo = new ZooManager(animals);

        // Виконання функціоналу
        myZoo.showAllAnimals();
        myZoo.showSpeciesCount();
        myZoo.calculateMonthlyFood();

        // Тест Equals
        Tiger t1 = new Tiger("Шерхан", 8.5);
        System.out.println("\nТест порівняння (Equals): " + t1.equals(new Tiger("Шерхан", 8.5)));
    }
}
