package task2.trip;
import lombok.Data;
import lombok.NonNull;
import task2.driver.Driver;
import task2.vehicle.Vehicle;

import java.time.LocalDate;

@Data
public class Trip {
    private Vehicle vehicle = null;
    private Driver driver = null;
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = null;
    @NonNull
    Double distance;
    @NonNull
    Address address;
    @NonNull
    Integer neededWeight;

    public Integer getNeededExperience() {
        return neededWeight % 5;
    }
}