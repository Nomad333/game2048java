package lab6.task5.buildings.factory;

import lab6.task5.buildings.Building;

import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRandomBuildingFactory<T extends Building> implements Factory<T> {
    private String[] addresses = {"123 Main St", "456 Elm St", "789 Oak St"};
    private String[] cities = {"New York", "Los Angeles", "Chicago"};
    private String[] owners = {"Alice", "Bob", "Charlie"};

    public AbstractRandomBuildingFactory() {
    }

    public AbstractRandomBuildingFactory(String[] addresses, String[] cities, String[] owners) {
        this.addresses = addresses;
        this.cities = cities;
        this.owners = owners;
    }

    public String getRandomAddress() {
        return addresses[ThreadLocalRandom.current().nextInt(addresses.length)];
    }

    public String getRandomCity() {
        return cities[ThreadLocalRandom.current().nextInt(cities.length)];
    }

    public String getRandomOwner() {
        return owners[ThreadLocalRandom.current().nextInt(owners.length)];
    }
    
}
