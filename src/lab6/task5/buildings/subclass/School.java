package lab6.task5.buildings.subclass;

import lab6.task5.buildings.AbstractBuilding;
import lab6.task5.buildings.factory.AbstractRandomBuildingFactory;

import java.util.concurrent.ThreadLocalRandom;

public class School extends AbstractBuilding {
    private int numberOfStudents = 0;
    private int accreditationLevel = 1;

    public School() {
    }

    public School(String street, String numberHouse, String owner) {
        super(street, numberHouse, owner);
    }

    public School(String street, String numberHouse, String owner, int kStudents, int accreditationLevel) {
        super(street, numberHouse, owner);
        this.accreditationLevel = accreditationLevel;
        setNumberOfStudents(kStudents);
    }


    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int kStudents) {
        if (kStudents < 0) throw new IllegalArgumentException("kStudents cannot be negative");
        var calc = accreditationLevel * kStudents;
        this.numberOfStudents = ThreadLocalRandom.current().nextInt(calc / 2, calc);
    }

    public int getAccreditationLevel() {
        return accreditationLevel;
    }

    public void setAccreditationLevel(int accreditationLevel) {
        this.accreditationLevel = accreditationLevel;
    }

    @Override
    public String toString() {
        return super.toString() +
                " -number of students: " + numberOfStudents + "\n" +
                " -accreditation level: " + accreditationLevel + "\n";
    }

    public static class SchoolFactory extends AbstractRandomBuildingFactory<School> {
        public SchoolFactory() {
            super();
        }

        public SchoolFactory(String[] addresses, String[] cities, String[] owners, String[] streetNames, String[] houseNumbers) {
            super(addresses, cities, owners, streetNames, houseNumbers);
        }

        @Override
        public School create() {
            return new School(getRandomStreetName(), getRandomHouseNumber(), getRandomOwner(),
                    ThreadLocalRandom.current().nextInt(100, 500),
                    ThreadLocalRandom.current().nextInt(1, 5));
        }
    }
}
