package com.parkland.parkinglot;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParkingLotTest {

    @Test
    public void testCreateParkingLot(){
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingSlot> slots = parkingLot.createParkingSlot(6);
        Assert.assertEquals(slots.size(),  6);
        for(int i = 0; i < 6; i++){
            Assert.assertEquals(slots.get(i).getSlotId(), i+1);
        }
    }
}
