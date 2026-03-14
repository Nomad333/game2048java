package task2;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import task2.driver.Driver;
import task2.trip.Trip;
import task2.vehicle.Vehicle;
import task2.vehicle.VehicleStatus;

import java.time.LocalDate;
import java.util.*;

@Getter
@ToString
@EqualsAndHashCode
public class DispatcherService {
    private Queue<Trip> pendingTrips = new ArrayDeque<>();
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Driver> drivers = new ArrayList<>();
    private Set<Trip> tripsInProgress = new HashSet<>();
    private final List<Trip> completedTrips = new ArrayList<>();

    public DispatcherService() {
    }

    @Builder
    public DispatcherService(Queue<Trip> pendingTrips, List<Vehicle> vehicles,
                             List<Driver> drivers, Set<Trip> tripsInProgress) {
        this.pendingTrips = pendingTrips;
        this.vehicles = vehicles;
        this.drivers = drivers;
        this.tripsInProgress = tripsInProgress;
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    public void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    public void removePendingTrip(Trip trip) {
        pendingTrips.remove(trip);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addTrip(Trip trip) {
        pendingTrips.add(trip);
    }

    public void driverRequestRepair(Driver driver) {
        tripsInProgress.stream()
                .filter(trip -> trip.getDriver().equals(driver))
                .forEach(trip -> trip.getVehicle().setStatus(VehicleStatus.REPAIR));
    }

    public void driverRequestOk(Driver driver) {
        tripsInProgress.stream()
                .filter(trip -> trip.getDriver().equals(driver))
                .forEach(trip -> trip.getVehicle().setStatus(VehicleStatus.OK));
    }

    public boolean assignNextTrip() {
        // TODO: Exceptions
        if (pendingTrips.isEmpty()) {
            System.err.println("No available trips");
            return false;
        }
        if (vehicles.isEmpty()) {
            System.err.println("No available vehicles");
            return false;
        }
        if (drivers.isEmpty()) {
            System.err.println("No available drivers");
            return false;
        }
        Trip trip = pendingTrips.poll();

        // Оптимальний автомобіль
        var vehicle = findOptimalVehicle(trip);
        if (vehicle.isEmpty()) {
            System.err.println("No available vehicles for trip vehicles.getMaxWeight() < neededWeight");
            return false;
        }

        // Оптимальний водій
        var driver = findOptimalDriver(trip);
        if (driver.isEmpty()) {
            System.err.println("No available drivers for trip driver.getExperience() < neededExperience");
            return false;
        }

        trip.setDriver(driver.get());
        trip.setVehicle(vehicle.get());
        trip.setStartDate(LocalDate.now());

        removeVehicle(vehicle.get());
        removeDriver(driver.get());
        removePendingTrip(trip);
        tripsInProgress.add(trip);
        return true;
    }

    private Optional<Vehicle> findOptimalVehicle(Trip trip) {
        var neededWeight = trip.getNeededWeight();

        return vehicles.stream()
                .filter(v -> v.getMaxWeight() >= neededWeight)
                .min(Comparator.comparing(Vehicle::getMaxWeight));
    }

    private Optional<Driver> findOptimalDriver(Trip trip) {
        var neededExperience = trip.getNeededExperience();

        return drivers.stream()
                .filter(d -> d.getExperience() >= neededExperience)
                .min(Comparator.comparing(Driver::getExperience));
    }

    public boolean completeTrip(Trip trip) {
        if (tripsInProgress.remove(trip)) {
            trip.setEndDate(LocalDate.now());
            completedTrips.add(trip);
            return true;
        } else {
            System.err.println("Trip not found in progress");
            return false;
        }
    }
}
