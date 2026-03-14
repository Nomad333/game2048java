package task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task2.driver.Driver;
import task2.trip.Address;
import task2.trip.Trip;
import task2.vehicle.DefaultVehicle;
import task2.vehicle.Vehicle;
import task2.vehicle.VehicleStatus;


import static org.junit.jupiter.api.Assertions.*;

class DispatcherServiceTest {

    private DispatcherService dispatcher;

    private Vehicle vehicle;
    private Driver driver;
    private Trip trip;

    @BeforeEach
    void setUp() {
        dispatcher = new DispatcherService();

        vehicle = new DefaultVehicle("Vehicle1", 1000, VehicleStatus.OK);

        driver = new Driver("John", "Doe", "123", 5);

        trip = new Trip(500.0, new Address("foo"), 500);

        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(driver);
        dispatcher.addTrip(trip);
    }

    @Test
    void assignNextTrip_success() {
        boolean result = dispatcher.assignNextTrip();

        assertTrue(result);
        assertEquals(1, dispatcher.getTripsInProgress().size());
        assertTrue(dispatcher.getPendingTrips().isEmpty());
    }

    @Test
    void assignNextTrip_noTrips() {
        dispatcher = new DispatcherService();
        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(driver);

        boolean result = dispatcher.assignNextTrip();

        assertFalse(result);
    }

    @Test
    void assignNextTrip_noVehicleForWeight() {
        dispatcher = new DispatcherService();

        Vehicle smallVehicle = new DefaultVehicle("SmallVehicle", 100, VehicleStatus.OK);

        dispatcher.addVehicle(smallVehicle);
        dispatcher.addDriver(driver);
        dispatcher.addTrip(trip);

        boolean result = dispatcher.assignNextTrip();

        assertFalse(result);
    }

    @Test
    void assignNextTrip_noDriverForExperience() {
        dispatcher = new DispatcherService();

        Driver inexperiencedDriver = new Driver("Bob", "Test", "123", 1);

        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(inexperiencedDriver);
        dispatcher.addTrip(trip);

        boolean result = dispatcher.assignNextTrip();

        assertTrue(result);
    }

    @Test
    void completeTrip_success() {
        dispatcher.assignNextTrip();

        Trip activeTrip = dispatcher.getTripsInProgress().iterator().next();

        boolean result = dispatcher.completeTrip(activeTrip);

        assertTrue(result);
        assertEquals(1, dispatcher.getCompletedTrips().size());
        assertTrue(dispatcher.getTripsInProgress().isEmpty());
    }

    @Test
    void completeTrip_notFound() {
        boolean result = dispatcher.completeTrip(trip);

        assertFalse(result);
    }

}