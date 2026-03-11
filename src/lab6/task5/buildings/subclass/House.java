package lab6.task5.buildings.subclass;

import lab6.task5.buildings.AbstractBuilding;
import lab6.task5.buildings.factory.AbstractRandomBuildingFactory;

import java.util.concurrent.ThreadLocalRandom;

public class House extends AbstractBuilding {
    private int residents = 0;

    public House() {
    }

    public House(String address, String city, String owner) {
        super(address, city, owner);
    }

    public House(String address, String city, String owner, int residents) {
        super(address, city, owner);
        this.residents = residents;
    }

    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return super.toString() +
                " -residents: " + residents + "\n";
    }

    public static class HouseFactory extends AbstractRandomBuildingFactory<House> {
        public HouseFactory() {
            super();
        }

        public HouseFactory(String[] addresses, String[] cities, String[] owners) {
            super(addresses, cities, owners);
        }

        @Override
        public House create() {
            return new House(getRandomAddress(), getRandomCity(), getRandomOwner(),
                    ThreadLocalRandom.current().nextInt(1, 10));
        }
    }
}
