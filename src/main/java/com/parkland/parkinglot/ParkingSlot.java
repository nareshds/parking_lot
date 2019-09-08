package com.parkland.parkinglot;

import com.parkland.vehicles.Vehicle;

import java.util.List;

/**
 *
 *
 */
public class ParkingSlot {
    private int slotId;
    private Vehicle vehicle;

    public ParkingSlot(){

    }

    public ParkingSlot(int slotId){
        setSlotId(slotId);
    }

    public ParkingSlot nearestParkingSlot(List<ParkingSlot> slots){
        return slots.get(0);
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
