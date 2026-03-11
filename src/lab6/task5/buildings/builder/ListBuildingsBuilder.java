package lab6.task5.buildings.builder;

import lab6.task5.buildings.Building;
import lab6.task5.buildings.subclass.Hospital;
import lab6.task5.buildings.subclass.House;
import lab6.task5.buildings.subclass.School;
import lab6.task5.buildings.subclass.Store;

import java.util.ArrayList;
import java.util.List;

public class ListBuildingsBuilder implements Builder<List<Building>> {
    private final List<Building> buildings = new ArrayList<>();

    public List<Building> getBuildings() {
        return buildings;
    }

    public ListBuildingsBuilder addBuildings(Class<? extends Building> buildingClass) {
        switch (buildingClass.getSimpleName()) {
            case "School":
                buildings.add(new School.SchoolFactory().create());
                break;
            case "House":
                buildings.add(new House.HouseFactory().create());
                break;
            case "Store":
                buildings.add(new Store.StoreFactory().create());
                break;
            case "Hospital":
                buildings.add(new Hospital.HospitalFactory().create());
                break;
            default:
                throw new IllegalArgumentException("Unsupported building class: " + buildingClass.getSimpleName());
        }
        return this;
    }

    public ListBuildingsBuilder addBuildings(Class<? extends Building> buildingClass, int count) {
        for (int i = 0; i < count; i++) {
            switch (buildingClass.getSimpleName()) {
                case "School":
                    buildings.add(new School.SchoolFactory().create());
                    break;
                case "House":
                    buildings.add(new House.HouseFactory().create());
                    break;
                case "Store":
                    buildings.add(new Store.StoreFactory().create());
                    break;
                case "Hospital":
                    buildings.add(new Hospital.HospitalFactory().create());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported building class: " + buildingClass.getSimpleName());
            }
        }
        return this;
    }

    @Override
    public List<Building> build() {
        var res = new ArrayList<>(buildings);
        buildings.clear();
        return res;
    }
}
