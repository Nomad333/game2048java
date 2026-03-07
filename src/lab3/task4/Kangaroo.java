package lab3.task4;

import java.util.Objects;

public class Kangaroo extends Animal {
    private double jumpHeight;

    public Kangaroo(String name, double dailyGreens) {
        super(name, "Кенгуру", dailyGreens, "Трава/Овочі", true, true);
        this.jumpHeight = 2.5;
    }

    @Override
    public String getHabitatRequirements() {
        return "Відкритий вольєр з високим парканом (мін. 3м) та піщаним ґрунтом. Може жити з іншими травоїдними.";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Kangaroo kangaroo)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(jumpHeight, kangaroo.jumpHeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jumpHeight);
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Висота стрибка: " + jumpHeight;
    }
}
