package com.parkland.parkinglot;

import com.parkland.vehicles.Vehicle;

/**
 *
 */
public interface IParkingLot {
    ParkingSlot parkVehicle(Vehicle vehicle);
    ParkingSlot unParkVehicle(Vehicle vehicle);
}
