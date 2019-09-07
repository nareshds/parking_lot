package com.parkland;

import com.parkland.parkinglot.ParkingLot;
import com.parkland.parkinglot.ParkingSlot;
import com.parkland.vehicles.Car;
import com.parkland.vehicles.Vehicle;
import com.parkland.enums.CarColor;
import com.parkland.enums.Queries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        ParkingLot lot = null;
        Queries query = Queries.valueOf(input[0]);
        switch (query) {
            case create_parking_lot:
                lot = new ParkingLot();
                lot.createParkingSLot(Integer.parseInt(input[1]));
                break;
            case park:
                Vehicle vehicle = new Car();
                vehicle.setRegNumber(input[1]);
                vehicle.setColor(CarColor.valueOf(input[2]));
                try{
                    lot.parkVehicle(vehicle);
                } catch (NullPointerException e){
                    throw new NullPointerException("No parking lot object found");
                }
                break;
            case leave:
                lot.unParkVehicle(Long.parseLong(input[1]));
                break;
            case status:
                lot.getLotStatus();
                break;
            case registration_numbers_for_cars_with_colour:
                List<Vehicle> cars =  lot.getRegNumbersFromColor(input[1]);
                for(Vehicle car : cars){
                    System.out.println(car.getRegNumber());
                }
                break;
            case slot_numbers_for_cars_with_colour:
                List<ParkingSlot> slots = lot.getSlotNumbersFromColor(input[1]);
                for(ParkingSlot slot : slots){
                    System.out.println(slot.getSlotId());
                }
                break;
            case slot_number_for_registration_number:
                slots = lot.getSlotNumberFromRegNumber(input[1]);
                for(ParkingSlot slot : slots){
                    System.out.println(slot.getSlotId());
                }
                break;
            default:
                throw new IllegalArgumentException("Not a valid query or input");

        }
    }
}
