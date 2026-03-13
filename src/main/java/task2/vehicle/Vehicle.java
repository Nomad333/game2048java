package task2.vehicle;

public interface Vehicle {
    String getName();
    String getMaxWeight();
    VehicleStatus getStatus();
    void setStatus(VehicleStatus status);
}
