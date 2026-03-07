package lab4.task1.factory;

import lab4.task1.Product;
import lab4.task1.ProductCategory;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomProductFactory extends AbstractProductFactory {

    private final String[] NAMES = {"Взуття", "Ручка", "Телевизор", "Телефон", "Шапка"};
    private final ProductCategory[] CATEGORIES = ProductCategory.values();
    private final String[] DESCRIPTIONS = {"НОВЕ", "Б/В"};

    @Override
    public Product create() {
        return new Product(
                randName(),
                randPrice(),
                randCategory(),
                randDesc(),
                randDate(),
                randAmount()
        );
    }

    private String randName() {
        int index = ThreadLocalRandom.current().nextInt(NAMES.length);
        return NAMES[index];
    }

    private ProductCategory randCategory() {
        int index = ThreadLocalRandom.current().nextInt(CATEGORIES.length);
        return CATEGORIES[index];
    }

    private String randDesc() {
        int index = ThreadLocalRandom.current().nextInt(DESCRIPTIONS.length);
        return DESCRIPTIONS[index];
    }

    private int randAmount() {
        return ThreadLocalRandom.current().nextInt(1, 101); // 1–100
    }

    private double randPrice() {
        return ThreadLocalRandom.current().nextDouble(10, 1000); // 10–1000
    }

    private LocalDate randDate() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.now();

        long startEpoch = start.toEpochDay();
        long endEpoch = end.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch);

        return LocalDate.ofEpochDay(randomDay);
    }
}
