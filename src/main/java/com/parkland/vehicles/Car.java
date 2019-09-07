package com.parkland.vehicles;

import com.parkland.enums.CarColor;

/**
 *
 */
public class Car extends Vehicle {

    public Car(){

    }

    public Car(String regNumber, CarColor color){
        super.setRegNumber(regNumber);
        super.setColor(color);

    }
    public Vehicle getCarInfo(){
        return super.getInfo();
    }
}
