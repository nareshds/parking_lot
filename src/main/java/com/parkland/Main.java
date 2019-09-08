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
        ParkingLot lot = null;
        String line;
        while((line = br.readLine()) != null){
            String[] input = line.split(Constants.SPACE);
            Queries query = Queries.valueOf(input[0]);
            switch (query) {
                case create_parking_lot:
                    lot = new ParkingLot();
                    lot.createParkingSlot(Integer.parseInt(input[1]));
                    break;
                case park:
                    Vehicle vehicle = new Car();
                    vehicle.setRegNumber(input[1]);
                    vehicle.setColor(CarColor.valueOf(input[2]));
                    try{
                        lot.parkVehicle(vehicle);
                    } catch (NullPointerException e){
                        throw new NullPointerException(Constants.ERROR_NO_PARKING_OBJECT);
                    }
                    break;
                case leave:
                    lot.unParkVehicle(Integer.parseInt(input[1]));
                    break;
                case status:
                    lot.getLotStatus();
                    break;
                case registration_numbers_for_cars_with_colour:
                    List<Vehicle> cars =  lot.getRegNumbersFromColor(input[1]);
                    StringBuilder builder = new StringBuilder();
                    for(Vehicle car : cars){
                        builder.append(car.getRegNumber()+ Constants.COMMA + Constants.SPACE);
                    }
                    if(cars.size() > 0){
                        System.out.println(builder.toString().substring(0, builder.lastIndexOf(Constants.COMMA)));
                    }
                    break;
                case slot_numbers_for_cars_with_colour:
                    List<Integer> slots = lot.getSlotNumbersFromColor(input[1]);
                    printResultAsString(slots);
                    break;
                case slot_number_for_registration_number:
                    slots = lot.getSlotNumberFromRegNumber(input[1]);
                    if (slots.size() == 0){
                        System.out.println(Constants.INFO_NOT_FOUND);
                    }
                    printResultAsString(slots);
                    break;
                case exit:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException(Constants.ERROR_ILLEGAL_ARG);
            }
        }
    }

    private static void printResultAsString(List<Integer> slots) {
        StringBuilder builder = new StringBuilder();
        for(Integer slot : slots){
            builder.append(slot+ Constants.COMMA+ Constants.SPACE);
        }
        if(slots.size() > 0){
            System.out.println(builder.toString().substring(0, builder.lastIndexOf(Constants.COMMA)));
        }
    }
}
