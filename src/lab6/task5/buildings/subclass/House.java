package lab6.task5.buildings.subclass;

import lab6.task5.buildings.AbstractBuilding;
import lab6.task5.buildings.factory.AbstractRandomBuildingFactory;

import java.util.concurrent.ThreadLocalRandom;

public class House extends AbstractBuilding {
    private int residents = 0;

    public House() {
    }

    public House(String street, String numberHouse, String owner) {
        super(street, numberHouse, owner);
    }

    public House(String street, String numberHouse, String owner, int residents) {
        super(street, numberHouse, owner);
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

        public HouseFactory(String[] addresses, String[] cities, String[] owners, String[] streetNames, String[] houseNumbers) {
            super(addresses, cities, owners, streetNames, houseNumbers);
        }

        @Override
        public House create() {
            return new House(getRandomStreetName(), getRandomHouseNumber(), getRandomOwner(),
                    ThreadLocalRandom.current().nextInt(1, 10));
        }
    }
}
