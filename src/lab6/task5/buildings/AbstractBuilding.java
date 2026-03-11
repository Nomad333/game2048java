package lab6.task5.buildings;

import java.util.Objects;

public abstract class AbstractBuilding implements Building {
    protected String address = "";
    protected String city = "";
    protected String owner = "";

    public AbstractBuilding() {
    }

    public AbstractBuilding(String address, String city, String owner) {
        this.address = address;
        this.city = city;
        this.owner = owner;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
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
                " -address: " + address + "\n" +
                " -city: " + city + "\n" +
                " -owner: " + owner + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractBuilding that)) return false;
        return Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, owner);
    }
}
