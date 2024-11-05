package com.parkingsystem;

public class VehicleSpace {
    private int spaceNumber;
    private boolean isAvailable;
    private Vehicle vehicle;

    public VehicleSpace(int spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.isAvailable = true;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public Vehicle removeVehicle() {
        Vehicle temp = this.vehicle;
        this.vehicle = null;
        this.isAvailable = true;
        return temp;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}