package com.parkland.parkinglot;

import com.parkland.Constants;
import com.parkland.vehicles.Vehicle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class implementing all the methods defined in the ParkingLot interface
 *
 */
public class ParkingLot implements IParkingLot {

    private static List<ParkingSlot> parkingSlots; //list of available parking slots
    private  Map<Integer, Vehicle> filledSlots;    //occupied slot info
    private int lotSize;  //defined lot size

    /**
     *
     * @param n: parking lot size
     * @return
     */
    public final List<ParkingSlot> createParkingSlot(Integer n){
        setLotSize(n);
        if(getParkingSlots() == null){
            buildParkingLot();
            setFilledSlots(new HashMap<Integer, Vehicle>());
            return getParkingSlots();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_ILLEGAL_ARG);
        }
    }

    /**
     *
     * @param vehicle
     * @return
     */
    @Override
    public ParkingSlot parkVehicle(Vehicle vehicle) {
        if(getParkingSlots().size() > 0){
            ParkingSlot nearestSlot = new ParkingSlot().nearestParkingSlot(getParkingSlots());
            nearestSlot.setVehicle(vehicle);
            getFilledSlots().put(nearestSlot.getSlotId(), vehicle);
            System.out.println(Constants.INFO_ALLOTTED_SLOT +nearestSlot.getSlotId());
            deleteHeapNode();
            return nearestSlot;
        } else {
            System.out.println(Constants.INFO_PARKING_FULL);
            return null;
        }
    }

    @Override
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
            getFilledSlots().remove(slotNumber);
            insertHeapNode(slot);
            System.out.println("Slot number " + slot.getSlotId() + " is free");
        }

    }

    @Override
    public void getLotStatus() {
        System.out.println("Slot No. \t Registration No \t Color");
        for( Integer slot : getFilledSlots().keySet()){
            Vehicle vehicle = getFilledSlots().get(slot);
            System.out.println(slot +"\t\t" + vehicle.getRegNumber()+ "\t"+vehicle.getColor());
        }
    }

    @Override
    public List<Vehicle> getRegNumbersFromColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        for(Integer slot : getFilledSlots().keySet()){
            Vehicle vehicle = getFilledSlots().get(slot);
            if(vehicle.getColor().toString().equals(color)){
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    @Override
    public List<Integer> getSlotNumbersFromColor(String color) {
        List<Integer> slots = new ArrayList<>();
        for(Integer slot : getFilledSlots().keySet()){
            Vehicle vehicle = getFilledSlots().get(slot);
            if(vehicle.getColor().toString().equals(color)){
                slots.add(slot);
            }
        }
        return slots;
    }

    @Override
    public List<Integer> getSlotNumberFromRegNumber(String regNum) {
        List<Integer> slots = new ArrayList<>();
        for(Integer slot : getFilledSlots().keySet()){
            Vehicle vehicle = getFilledSlots().get(slot);
            if(vehicle.getRegNumber().equals(regNum)){
                slots.add(slot);
            }
        }
        return slots;
    }

    //Helper Methods
    /**
     *
     */
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
        System.out.println(Constants.INFO_LOT_CREATED+ Constants.SPACE + getLotSize() +Constants.SPACE+Constants.SLOTS);
    }

    /**
     *
     * @param i
     * @param slot
     */
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


    /**
     * delete parking slot as unavailable once occupied by a vehicle
     */
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

    /**
     *
     * @param pos
     * @return
     */
    private int parentNode(int pos) {
        return pos % 2 == 1 ? pos/2 : (pos-1)/2;
    }

    /**
     *
     * @param slot
     */
    private void insertHeapNode(ParkingSlot slot){
        int heapSize = getParkingSlots().size();
        if(heapSize < getLotSize()){
            getParkingSlots().add(slot);
            minHeapify(parentNode(heapSize), getParkingSlots().get(parentNode(heapSize)));
        } else {
            System.out.println("TODO");
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

    public Map<Integer, Vehicle> getFilledSlots() {
        return filledSlots;
    }

    public void setFilledSlots(Map<Integer, Vehicle> filledSlots) {
        this.filledSlots = filledSlots;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
}
