package com.parkland.parkinglot;

import com.parkland.enums.CarColor;
import com.parkland.vehicles.Vehicle;

import java.util.List;

/**
 * Interface for parking lot
 * implements required use cases
 * 1. Park a vehicle(car)
 * 2. Unpark a vehicle(car)
 * 3. get parking lot status
 * 4. get vehicle reg# from color
 * 5. get parking slot from vehicle color
 * 6. get parking slot from vehicle reg#
 */
public interface IParkingLot {
    ParkingSlot parkVehicle(Vehicle vehicle);

    void unParkVehicle(int slot);

    void getLotStatus();

    List<Vehicle> getRegNumbersFromColor(CarColor color);

    List<Integer> getSlotNumbersFromColor(CarColor color);

    Integer getSlotNumberFromRegNumber(String regNum);
}

