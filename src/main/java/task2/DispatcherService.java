package task2;

import lombok.*;
import task2.driver.Driver;
import task2.trip.Trip;
import task2.vehicle.Vehicle;
import task2.vehicle.VehicleStatus;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class DispatcherService {
    @Builder.Default
    private Queue<Trip> pendingTrips = new ArrayDeque<>();
    @Builder.Default
    private List<Vehicle> vehicles = new ArrayList<>();
    @Builder.Default
    private List<Driver> drivers = new ArrayList<>();
    @Builder.Default
    private Set<Trip> tripsInProgress = new HashSet<>();
    private final List<Trip> completedTrips = new ArrayList<>();
    @Builder.Default
    private StreamLogger<Trip> tripLogger = new StreamLogger<>(Path.of("trips.log"));

    public DispatcherService(Queue<Trip> pendingTrips, List<Vehicle> vehicles,
                             List<Driver> drivers, Set<Trip> tripsInProgress,
                             StreamLogger<Trip> tripLogger) {
        this.pendingTrips = pendingTrips;
        this.vehicles = vehicles;
        this.drivers = drivers;
        this.tripsInProgress = tripsInProgress;
        this.tripLogger = tripLogger;
    }

    public void logTrip(Trip trip) {
        tripLogger.append(trip);
    }

    public void clearLog() {
        tripLogger.clear();
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
            vehicles.add(trip.getVehicle());
            drivers.add(trip.getDriver());
            completedTrips.add(trip);
            logTrip(trip);
            return true;
        } else {
            System.err.println("Trip not found in progress");
            return false;
        }
    }

    public void printStatistics() {
        System.out.println("Completed trips: " + completedTrips.size());
        System.out.println("Completed trips by driver:");
        completedTrips.stream()
                .map(Trip::getDriver)
                .distinct()
                .forEach(driver -> System.out.println(driver + ": " + completedTrips.stream()
                        .filter(t -> t.getDriver().equals(driver))
                        .count()));
    }
}
