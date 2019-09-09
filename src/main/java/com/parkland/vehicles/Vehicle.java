package com.parkland.vehicles;

import com.parkland.enums.CarColor;

/**
 * Vehicle class
 */
public abstract class Vehicle {

    private String regNumber;
    private CarColor color;

    Vehicle getInfo(){
        return this;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }
}
