package lab3.task4;

import java.util.Objects;

public class Tiger extends Animal {
    private double biteForce; // Сила укусу

    public Tiger(String name, double dailyMeat) {
        super(name, "Тигр", dailyMeat, "Яловичина/Кролятина", false, false);
        this.biteForce = 0.85;
    }

    @Override
    public String getHabitatRequirements() {
        return "Потребує вольєр з густою рослинністю та водоймою для плавання. Сусідство заборонено.";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), biteForce);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Tiger tiger)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(biteForce, tiger.biteForce) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Сила укусу: " + biteForce;
    }
}