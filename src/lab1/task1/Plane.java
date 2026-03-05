package lab1.task1;

public class Plane implements Consumable {
    private int tankValue;
    private final int storageValue;

    public Plane(int tankValue, int storageValue) {
        this.tankValue = tankValue;
        this.storageValue = storageValue;
    }

    @Override
    public int getConsumption() {
        return switch ((Integer) storageValue) {
            case Integer val when val < 500 -> 1;
            case Integer val when val < 1000 -> 4;
            case Integer val when val < 1500 -> 7;
            case Integer val when val < 2000 -> 9;
            default -> throw new RuntimeException("Value Storage > 2000");
        };
    }

    public int calculateValueTank(int... distancesKm) {
        int sum = 0;
        for (var item : distancesKm) {
            sum += item * getConsumption();
        }
        return sum;
    }

    public boolean isCanFly(int... distancesKm) {
        return tankValue >= calculateValueTank(distancesKm);
    }

    public int calculateRestValueTank(int... distancesKm) {
        return tankValue - calculateValueTank(distancesKm);
    }
}
