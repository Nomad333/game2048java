package lab6.task5.buildings;

import java.util.Objects;

public abstract class AbstractBuilding implements Building {
    protected String street = "";
    protected String numberHouse = "";
    protected String owner = "";

    public AbstractBuilding() {
    }

    public AbstractBuilding(String street, String numberHouse, String owner) {
        this.street = street;
        this.numberHouse = numberHouse;
        this.owner = owner;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getNumberHouse() {
        return numberHouse;
    }

    @Override
    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Building:" + "\n" +
                " -street: " + street + "\n" +
                " -number house: " + numberHouse + "\n" +
                " -owner: " + owner + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractBuilding that)) return false;
        return Objects.equals(street, that.street) && Objects.equals(numberHouse, that.numberHouse) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, numberHouse, owner);
    }
}
