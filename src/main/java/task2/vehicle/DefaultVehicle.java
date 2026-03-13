package task2.vehicle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultVehicle implements Vehicle  {
    private String name;
    private String maxWeight;
    private VehicleStatus status;
}
