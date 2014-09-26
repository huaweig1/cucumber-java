package com.sears.ge.dto;

/**
 *
 * @author Q
 */
public class Car {

    private int wheels;

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public boolean canDrive() {
        return wheels == 4;
    }
}
