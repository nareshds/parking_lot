package parkinglot;

import vehicles.Vehicle;

public interface ParkingLot {
    ParkingSlot parkVehicle(Vehicle vehicle);
    ParkingSlot unParkVehicle(Vehicle vehicle);

}
