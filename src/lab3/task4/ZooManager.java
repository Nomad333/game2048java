package lab3.task4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooManager {
    private List<Animal> animals = new ArrayList<>();

    public ZooManager(List<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void showAllAnimals() {
        System.out.println("=== Список мешканців зоопарку ===");
        for (Animal a : animals) {
            System.out.println(a);
            System.out.println("Вимоги: " + a.getHabitatRequirements());
            System.out.println("---------------------------------");
        }
    }

    public void showSpeciesCount() {
        Map<String, Integer> counts = new HashMap<>();
        for (Animal a : animals) {
            counts.put(a.getSpecies(), counts.getOrDefault(a.getSpecies(), 0) + 1);
        }
        System.out.println("Кількість за видами: " + counts);
    }

    public void calculateMonthlyFood() {
        double totalFood = 0;
        for (Animal a : animals) {
            totalFood += a.getDailyFoodAmount() * 30; // Розрахунок на 30 днів
        }
        System.out.printf("Загальна потреба корму на місяць: %.2f кг\n", totalFood);
    }
}
