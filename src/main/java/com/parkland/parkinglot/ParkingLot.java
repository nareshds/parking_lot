package com.parkland.parkinglot;

import com.parkland.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class ParkingLot implements IParkingLot {

    private static List<ParkingSlot> parkingSlots;

    public List<ParkingSlot> createParkingSLot(Integer n){
        setParkingSlots(new ArrayList<ParkingSlot>(n));
        return getParkingSlots();
    }

    public ParkingSlot parkVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public ParkingSlot unParkVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public void getLotStatus() {

    }

    @Override
    public List<Vehicle> getRegNumbersFromColor(String color) {
        return null;
    }

    @Override
    public List<ParkingSlot> getSlotNumbersFromColor(String color) {
        return null;
    }

    @Override
    public List<ParkingSlot> getSlotNumberFromRegNumber(String regNum) {
        return null;
    }

    public ParkingSlot unParkVehicle(long slot) {
        return null;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }
}
