package task2;

import org.junit.jupiter.api.Test;
import task2.vehicle.DefaultVehicle;
import task2.vehicle.VehicleStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    @Test
    public void testVehicleStatus() {
        DefaultVehicle vehicle = DefaultVehicle.builder()
                .name("Car")
                .maxWeight("1000")
                .status(VehicleStatus.OK)
                .build();

        assertEquals(VehicleStatus.OK, vehicle.getStatus());

        vehicle.setStatus(VehicleStatus.BROKEN);
        assertEquals(VehicleStatus.BROKEN, vehicle.getStatus());
    }

}
