package lab6.task5.buildings.subclass;

import lab6.task5.buildings.AbstractBuilding;
import lab6.task5.buildings.factory.AbstractRandomBuildingFactory;

import java.util.concurrent.ThreadLocalRandom;

public class Hospital extends AbstractBuilding {
    private int numberOfDoctors = 0;

    public Hospital() {
    }

    public Hospital(String address, String city, String owner) {
        super(address, city, owner);
    }

    public Hospital(String address, String city, String owner, int numberOfDoctors) {
        super(address, city, owner);
        this.numberOfDoctors = numberOfDoctors;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public void setNumberOfDoctors(int numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    @Override
    public String toString() {
        return super.toString() +
                " -number of doctors: " + numberOfDoctors + "\n";
    }

    public static class HospitalFactory extends AbstractRandomBuildingFactory<Hospital> {
        public HospitalFactory() {
            super();
        }

        public HospitalFactory(String[] addresses, String[] cities, String[] owners) {
            super(addresses, cities, owners);
        }

        @Override
        public Hospital create() {
            return new Hospital(getRandomAddress(), getRandomCity(), getRandomOwner(),
                    ThreadLocalRandom.current().nextInt(10, 50));
        }
    }
}