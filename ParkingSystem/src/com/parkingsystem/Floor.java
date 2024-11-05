package com.parkingsystem;

import java.util.HashMap;
import java.util.Map;

public class Floor {
    private int floorNumber;
    private Map<String, VehicleSpace[]> vehicleSpaces;

    public Floor(int floorNumber, int bikeSpaces, int carSpaces, int truckSpaces, int busSpaces) {
        this.floorNumber = floorNumber;
        vehicleSpaces = new HashMap<>();
        vehicleSpaces.put("Bike", new VehicleSpace[bikeSpaces]);
        vehicleSpaces.put("Car", new VehicleSpace[carSpaces]);
        vehicleSpaces.put("Truck", new VehicleSpace[truckSpaces]);
        vehicleSpaces.put("Bus", new VehicleSpace[busSpaces]);
        
        for (int i = 0; i < bikeSpaces; i++) {
            vehicleSpaces.get("Bike")[i] = new VehicleSpace(i + 1);
        }
        for (int i = 0; i < carSpaces; i++) {
            vehicleSpaces.get("Car")[i] = new VehicleSpace(i + 1);
        }
        for (int i = 0; i < truckSpaces; i++) {
            vehicleSpaces.get("Truck")[i] = new VehicleSpace(i + 1);
        }
        for (int i = 0; i < busSpaces; i++) {
            vehicleSpaces.get("Bus")[i] = new VehicleSpace(i + 1);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Map<String, VehicleSpace[]> getVehicleSpaces() {
        return vehicleSpaces;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        VehicleSpace[] spaces = vehicleSpaces.get(vehicle.getType());
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                space.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (VehicleSpace[] spaces : vehicleSpaces.values()) {
            for (VehicleSpace space : spaces) {
                if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space.removeVehicle();
                }
            }
        }
        return null;
    }

    public int checkAvailability(String vehicleType) {
        VehicleSpace[] spaces = vehicleSpaces.get(vehicleType);
        int availableCount = 0;
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                availableCount++;
            }
        }
        return availableCount;
    }
}