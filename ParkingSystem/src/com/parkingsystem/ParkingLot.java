package com.parkingsystem;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<Floor> floors;
    private CostStrategy costStrategy;
    private int totalCapacity;

    public ParkingLot(int numberOfFloors, int bikeSpaces, int carSpaces, int truckSpaces, int busSpaces) {
        floors = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i + 1, bikeSpaces, carSpaces, truckSpaces, busSpaces));
        }
        totalCapacity = numberOfFloors * (carSpaces + truckSpaces + busSpaces);
        costStrategy = new CostStrategy();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        return floors.stream().anyMatch(floor -> floor.parkVehicle(vehicle));
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (Floor floor : floors) {
            Vehicle vehicle = floor.removeVehicle(registrationNumber);
            if (vehicle != null) {
                Duration duration = Duration.between(vehicle.getEntryTime(), LocalDateTime.now());
                long totalMinutes = duration.toMinutes(); // Get total minutes parked
                
                int cost;
                if (totalMinutes <= 60) {
                    cost = 10; 
                } else if (totalMinutes <= 120) {
                    cost = 20; 
                } else {
                    
                    long additionalHours = (totalMinutes - 120) / 60;
                    cost = 20 + (int)(additionalHours + 1) * 10; 
                }
                
                System.out.printf("Vehicle removed: %s. Total cost: Rs.%d%n", vehicle.getRegistrationNumber(), cost);
                return vehicle; 
            }
        }
        return null; 
    }

    public int checkAvailability(String vehicleType) {
        return floors.stream().mapToInt(floor -> floor.checkAvailability(vehicleType)).sum();
    }

    public void configureCostStrategy(CostStrategy strategy) {
        this.costStrategy = strategy;
    }

    public CostStrategy getCostStrategy() {
        return costStrategy;
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            for (String type : floor.getVehicleSpaces().keySet()) {
                int available = floor.checkAvailability(type);
                System.out.println("  " + type + " - Available: " + available);
            }
        }
    }
}