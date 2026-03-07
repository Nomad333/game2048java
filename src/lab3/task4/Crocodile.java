package lab3.task4;

import java.util.Objects;

public class Crocodile extends Animal {
    private int waterTemperature; // Необхідна температура води

    public Crocodile(String name, double dailyFish) {
        super(name, "Крокодил", dailyFish, "Риба/М'ясо птиці", true, true);
        this.waterTemperature = 28;
    }

    @Override
    public String getHabitatRequirements() {
        return "Тераріум з підігрівом води до " + waterTemperature + "°C та зоною сонячного світла (УФ-лампи).";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), waterTemperature);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Crocodile crocodile)) return false;
        if (!super.equals(o)) return false;
        return waterTemperature == crocodile.waterTemperature;
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Температура води: " + waterTemperature;
    }
}
