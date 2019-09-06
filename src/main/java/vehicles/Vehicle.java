package vehicles;

import vehicles.enums.CarColor;

public abstract class Vehicle {

    private String regNumber;
    private CarColor color;

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
