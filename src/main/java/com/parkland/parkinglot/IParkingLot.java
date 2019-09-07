package com.parkland.parkinglot;

import com.parkland.vehicles.Vehicle;

import java.util.List;

/**
 *
 */
public interface IParkingLot {
    ParkingSlot parkVehicle(Vehicle vehicle);
    ParkingSlot unParkVehicle(Vehicle vehicle);

    void getLotStatus();
    List<Vehicle> getRegNumbersFromColor(String color);

    List<ParkingSlot> getSlotNumbersFromColor(String color);

    List<ParkingSlot> getSlotNumberFromRegNumber(String regNum);
}

