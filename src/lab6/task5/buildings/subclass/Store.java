package lab6.task5.buildings.subclass;

import lab6.task5.buildings.AbstractBuilding;
import lab6.task5.buildings.factory.AbstractRandomBuildingFactory;

import java.util.concurrent.ThreadLocalRandom;

public class Store extends AbstractBuilding {
    private int departments = 1;

    public Store() {
    }

    public Store(String street, String numberHouse, String owner) {
        super(street, numberHouse, owner);
    }

    public Store(String street, String numberHouse, String owner, int departments) {
        super(street, numberHouse, owner);
        this.departments = departments;
    }

    public int getDepartments() {
        return departments;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return super.toString() +
                " -departments: " + departments + "\n";
    }

    public static class StoreFactory extends AbstractRandomBuildingFactory<Store> {
        public StoreFactory() {
            super();
        }

        public StoreFactory(String[] addresses, String[] cities, String[] owners, String[] streetNames, String[] houseNumbers) {
            super(addresses, cities, owners, streetNames, houseNumbers);
        }

        @Override
        public Store create() {
            return new Store(getRandomStreetName(), getRandomHouseNumber(), getRandomOwner(),
                    ThreadLocalRandom.current().nextInt(1, 10));
        }
    }
}
