package lab3.task4;

import java.util.Objects;

public abstract class Animal {
    protected String name;
    protected String species;
    protected double dailyFoodAmount; // у кг
    protected String foodType;
    protected boolean canBreedInCaptivity;
    protected boolean sociableWithOwnKind;

    public Animal(String name, String species, double dailyFoodAmount, String foodType,
                  boolean canBreedInCaptivity, boolean sociableWithOwnKind) {
        this.name = name;
        this.species = species;
        this.dailyFoodAmount = dailyFoodAmount;
        this.foodType = foodType;
        this.canBreedInCaptivity = canBreedInCaptivity;
        this.sociableWithOwnKind = sociableWithOwnKind;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public double getDailyFoodAmount() {
        return dailyFoodAmount;
    }

    public String getFoodType() {
        return foodType;
    }

    public abstract String getHabitatRequirements();

    @Override
    public String toString() {
        return String.format("Тварина: %s (%s)\n- Корм: %s (%.2f кг/день)\n- Розмноження в неволі: %s\n- Соціальність: %s",
                name, species, foodType, dailyFoodAmount,
                canBreedInCaptivity ? "Так" : "Ні",
                sociableWithOwnKind ? "Дружня до своїх" : "Одинак");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Animal animal)) return false;
        return Double.compare(dailyFoodAmount, animal.dailyFoodAmount) == 0
                && canBreedInCaptivity == animal.canBreedInCaptivity
                && sociableWithOwnKind == animal.sociableWithOwnKind
                && Objects.equals(name, animal.name)
                && Objects.equals(species, animal.species)
                && Objects.equals(foodType, animal.foodType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species, dailyFoodAmount, canBreedInCaptivity, sociableWithOwnKind);
    }
}