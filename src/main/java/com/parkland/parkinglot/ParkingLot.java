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
    //private List<Vehicle> filledSlots;
    private int lotSize;

    public final List<ParkingSlot> createParkingSlot(Integer n){
        setLotSize(n);
        buildParkingLot();
        return getParkingSlots();
    }

    private void buildParkingLot() {
        setParkingSlots(new ArrayList<>(getLotSize()));
        for(int i = 1; i <= getLotSize(); i++){
            ParkingSlot slot = new ParkingSlot(i);
            getParkingSlots().add(slot);
        }
        int nonLeafNodeIndex = getLotSize()/2;
        for(int i = 0; i <= nonLeafNodeIndex; i++){
            minHeapify(i, getParkingSlots().get(i));
        }
        System.out.println("Created a parking lot with "+ getLotSize() +" slots");
    }

    private void minHeapify(Integer i, ParkingSlot slot) {
        ParkingSlot nearestSlot = slot;
        int leftNodeIndx = 2 * i + 1;
        int rightNodeIndx = 2 * i + 2;
        boolean leftSwap = false;
        boolean rightSwap = false;

        if(leftNodeIndx < getParkingSlots().size() && getParkingSlots().get(leftNodeIndx).getSlotId() < nearestSlot.getSlotId()){
            nearestSlot = getParkingSlots().get(leftNodeIndx);
            leftSwap = true;
        }

        if(rightNodeIndx < getParkingSlots().size() && getParkingSlots().get(rightNodeIndx).getSlotId() < nearestSlot.getSlotId()) {
            nearestSlot = getParkingSlots().get(rightNodeIndx);
            rightSwap = true;
            leftSwap = false;
        }

        if(nearestSlot.getSlotId() != slot.getSlotId()){
            getParkingSlots().set(i, nearestSlot);
            if(leftSwap) {
                getParkingSlots().set(leftNodeIndx, slot);
                minHeapify(leftNodeIndx, slot);
            }
            else if(rightSwap) {
                getParkingSlots().set(rightNodeIndx, slot);
                minHeapify(rightNodeIndx, slot);
            }
        }
    }

    public ParkingSlot parkVehicle(Vehicle vehicle) {
        if(getParkingSlots().size() > 0){
            ParkingSlot nearestSlot = new ParkingSlot().nearestParkingSlot(getParkingSlots());
            nearestSlot.setVehicle(vehicle);
            //getFilledSlots().add(nearestSlot.getSlotId(), vehicle);
            System.out.println("Allocated slot number: "+nearestSlot.getSlotId());
            deleteHeapNode();
            return nearestSlot;
        } else {
            System.out.println("Sorry, parking lot is full");
            return null;
        }
    }

    private void deleteHeapNode() {
        int heapSize = getParkingSlots().size();
        if(heapSize > 0){
            int rootNodeIndx = 0;
            ParkingSlot leafNode = getParkingSlots().get(heapSize-1);
            getParkingSlots().set(rootNodeIndx, leafNode);
            getParkingSlots().remove(heapSize-1);
            if(getParkingSlots().size() > 0){
                minHeapify(0, getParkingSlots().get(0));
            }
        }
    }

    private int parentNode(int pos) {
        return pos % 2 == 1 ? pos/2 : (pos-1)/2;
    }

    private void insertHeapNode(ParkingSlot slot){
        int heapSize = getParkingSlots().size();
        if(heapSize < getLotSize()){
            getParkingSlots().add(slot);
            minHeapify(parentNode(heapSize), getParkingSlots().get(parentNode(heapSize)));
        } else {
            System.out.println("TODO");
        }

    }

    @Override
    public void getLotStatus() {
        /*List<Vehicle> vehicles = getFilledSlots();
        System.out.println("Slot No. \t Registration No \t Color");
        for(int i = 0; i <  vehicles.size(); i++){
            if(vehicles.get(i) != null){
                System.out.println((i+1) +"\t" + vehicles.get(i).getRegNumber()+ "\t"+vehicles.get(i).getColor());
            }
        }*/
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

    public void unParkVehicle(int slotNumber) {
        List<ParkingSlot> slots = getParkingSlots();
        boolean unParkable = true;
        for(ParkingSlot slot : slots){
            if(slot.getSlotId() == slotNumber){
                System.out.println("Trying to un-park non-existing vehicle");
                unParkable = false;
            }
        }
        if(unParkable) {
            ParkingSlot slot = new ParkingSlot(slotNumber);
            insertHeapNode(slot);
            System.out.println("Slot number " + slot.getSlotId() + " is free");
        }

    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }


    public int getLotSize() {
        return lotSize;
    }

    /*public List<Vehicle> getFilledSlots() {
        return filledSlots;
    }

    public void setFilledSlots(List<Vehicle> filledSlots) {
        this.filledSlots = filledSlots;
    }*/

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
}
