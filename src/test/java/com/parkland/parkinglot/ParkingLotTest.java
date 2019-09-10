package com.parkland.parkinglot;


import com.parkland.Constants;
import com.parkland.enums.CarColor;
import com.parkland.vehicles.Car;
import com.parkland.vehicles.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;

public class ParkingLotTest {


    OutputStream os = new ByteArrayOutputStream();;
    @Before
    public void setSystemOut(){
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    public void testCreateParkingLot(){
        ParkingLot lot = new ParkingLot();
        int n = 10;
        List<ParkingSlot> slots = lot.createParkingSlot(n);

        Assert.assertEquals(os.toString(), Constants.INFO_LOT_CREATED+" " +n+" "+Constants.SLOTS+"\n");
        Assert.assertEquals(slots.size(),  n);
        for(int i = 0; i < 6; i++){
            Assert.assertEquals(slots.get(i).getSlotId(), i+1);
        }
        lot.setParkingSlots(null);
    }


    @Test
    public void testParkVehicle(){

        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        Assert.assertEquals(os.toString().split("\n")[1], Constants.INFO_ALLOTTED_SLOT+"1");
        lot.parkVehicle(v2);
        Assert.assertEquals(os.toString().split("\n")[2], Constants.INFO_ALLOTTED_SLOT+"2");
        lot.parkVehicle(v3);
        Assert.assertEquals(os.toString().split("\n")[3], Constants.INFO_ALLOTTED_SLOT+"3");

        lot.parkVehicle(v4);
        Assert.assertEquals(os.toString().split("\n")[4], Constants.INFO_ALLOTTED_SLOT+"4");

        lot.parkVehicle(v5);
        Assert.assertEquals(os.toString().split("\n")[5], Constants.INFO_ALLOTTED_SLOT+"5");

        lot.parkVehicle(v6);
        Assert.assertEquals(os.toString().split("\n")[6], Constants.INFO_ALLOTTED_SLOT+"6");

        lot.parkVehicle(new Car("AP-09-1234", CarColor.Brown));
        Assert.assertEquals(os.toString().split("\n")[7], Constants.INFO_PARKING_FULL);

        lot.setParkingSlots(null);
    }

    @Test
    public void testUnParkVehicle(){
        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);
        lot.parkVehicle(v6);

        lot.unParkVehicle(4);
        Assert.assertEquals(os.toString().split("\n")[7], Constants.SLOT_NUMBER+"4"+Constants.IS_FREE);

        lot.unParkVehicle(2);
        Assert.assertEquals(os.toString().split("\n")[7], Constants.SLOT_NUMBER+"4"+Constants.IS_FREE);

        ParkingSlot nearestSlot = new ParkingSlot().nearestParkingSlot(lot.getParkingSlots());
        Assert.assertEquals(nearestSlot.getSlotId(), 2);

        lot.setParkingSlots(null);
    }

    @Test
    public void testLotStatus(){
        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);
        lot.parkVehicle(v6);

        lot.unParkVehicle(4);

        Assert.assertEquals(lot.getFilledSlots().keySet().size(), 5);
        Map<Integer, Vehicle> expected = new HashMap<>();
        expected.put(1, v1);
        expected.put(2, v2);
        expected.put(3, v3);
        expected.put(5, v5);
        expected.put(6, v6);

        Assert.assertThat(lot.getFilledSlots(), is(expected));
        Assert.assertNull(lot.getFilledSlots().get(4));
        lot.setParkingSlots(null);
    }

    @Test
    public void testRegNumbersWithColor(){
        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);
        lot.parkVehicle(v6);

        List<Vehicle> vehicles = lot.getRegNumbersFromColor(CarColor.White);
        Assert.assertEquals(vehicles.size(), 2);
        List<Vehicle> expectedVehicles = new ArrayList<>();
        expectedVehicles.add(v1);
        expectedVehicles.add(v2);
        Assert.assertThat(vehicles, is(expectedVehicles));

        vehicles = lot.getRegNumbersFromColor(CarColor.Black);
        Assert.assertEquals(vehicles.size(), 2);
        expectedVehicles = new ArrayList<>();
        expectedVehicles.add(v3);
        expectedVehicles.add(v6);
        Assert.assertThat(vehicles, is(expectedVehicles));

        vehicles = lot.getRegNumbersFromColor(CarColor.Blue);
        Assert.assertEquals(vehicles.size(), 1);
        expectedVehicles = new ArrayList<>();
        expectedVehicles.add(v5);
        Assert.assertThat(vehicles, is(expectedVehicles));

        lot.setParkingSlots(null);
    }

    @Test
    public void testSlotNumberFromColor(){
        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);
        lot.parkVehicle(v6);

        List<Integer> vehicles = lot.getSlotNumbersFromColor(CarColor.White);
        Assert.assertEquals(vehicles.size(), 2);
        List<Integer> expectedSlots = new ArrayList<>();
        expectedSlots.add(1);
        expectedSlots.add(2);
        Assert.assertThat(vehicles, is(expectedSlots));

        lot.unParkVehicle(4);
        lot.parkVehicle(new Car("KA-01-P-333", CarColor.White));

        vehicles = lot.getSlotNumbersFromColor(CarColor.White);
        Assert.assertEquals(vehicles.size(), 3);
        expectedSlots = new ArrayList<>();
        expectedSlots.add(1);
        expectedSlots.add(2);
        expectedSlots.add(4);
        Assert.assertThat(vehicles, is(expectedSlots));

        lot.setParkingSlots(null);
    }

    public void testSlotNumberFromRegNumber(){
        ParkingLot lot = new ParkingLot();
        int n = 6;
        lot.createParkingSlot(n);

        Vehicle v1 = new Car("KA-01-HH-1234", CarColor.White);
        Vehicle v2 = new Car("KA-01-HH-9999", CarColor.White);
        Vehicle v3 = new Car("KA-01-HH-9999", CarColor.Black);
        Vehicle v4 = new Car("KA-01-BB-0001", CarColor.Red);
        Vehicle v5 = new Car("KA-01-HH-2701", CarColor.Blue);
        Vehicle v6 = new Car("KA-01-HH-3141", CarColor.Black);

        lot.parkVehicle(v1);
        lot.parkVehicle(v2);
        lot.parkVehicle(v3);
        lot.parkVehicle(v4);
        lot.parkVehicle(v5);
        lot.parkVehicle(v6);

        Integer slot = lot.getSlotNumberFromRegNumber("KA-01-HH-3141");
        Assert.assertEquals(slot.intValue(), 6);

        slot = lot.getSlotNumberFromRegNumber("AP-09-1234");
        Assert.assertNull(slot);

        lot.setParkingSlots(null);
    }


}
