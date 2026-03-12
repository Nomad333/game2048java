package lab6.task5;

import lab6.task5.buildings.Building;
import lab6.task5.buildings.subclass.House;
import lab6.task5.buildings.subclass.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class BuildingsManager {
    private List<Building> buildings = new ArrayList<>();

    public BuildingsManager() {
    }

    public BuildingsManager(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void addBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public List<Building> getBuildingsByType(Class<? extends Building> buildingClass) {
        return buildings.stream()
                .filter(buildingClass::isInstance)
                .toList();
    }

    //PECS — Producer Extends, Consumer Super
    public List<Building> getBuildingsBy(Class<? extends Building> buildingClass, Predicate<? super Building> predicate) {
        return buildings.stream()
                .filter(buildingClass::isInstance)
                .filter(predicate)
                .toList();
    }

    public void showPrivateHouses() {
        var privateHouses = getBuildingsByType(House.class);
        System.out.println("Private houses:");
        System.out.println(privateHouses);
    }

    //Зробити спосіб. який для випадково
    //    обраного житлового будинку знаходить у заданій околиці (певну кількість будинків від
    //    адреси будинку) усі магазини, що мають відділ заданого типу.
    public List<Building> getRandomHouseAndFindLocalStoresByLevel(int level) {
        var houses = getBuildingsByType(House.class);
        var randomHouse = houses.get(ThreadLocalRandom.current().nextInt(houses.size()));
        return getBuildingsBy(Store.class, store -> store
                .getStreet()
                .equals(randomHouse.getStreet())
                && ((Store) store).getDepartments() == level);
    }
}
