package task2.trip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TripTest {
    private Trip trip;

    @BeforeEach
    void setUp() {
        trip = new Trip(500.0, new Address("foo"), 500);
    }


    @Test
    void testTripParamsNotNull() {
        assertNotNull(trip.getAddress());
        assertNotNull(trip.getDistance());
        assertNotNull(trip.getNeededWeight());
    }

    @Test
    void testTripEquals() {
        Trip trip2 = new Trip(500.0, new Address("foo"), 500);
        assertEquals(trip, trip2);
    }
}
