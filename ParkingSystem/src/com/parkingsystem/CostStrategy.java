package com.parkingsystem;

import java.util.HashMap;
import java.util.Map;

public class CostStrategy {
    private Map<String, Integer> costPerHour; // Cost for subsequent hours
    private Map<String, Integer> initialCost; // Cost for the first hour

    public CostStrategy() {
        costPerHour = new HashMap<>();
        initialCost = new HashMap<>();

        
        initialCost.put("Bike", 10);  // Rs. 10 for the first hour for bikes
        initialCost.put("Car", 20);   // Rs. 20 for the first hour for cars
        initialCost.put("Truck", 30);  // Rs. 30 for the first hour for trucks
        initialCost.put("Bus", 30);    // Rs. 30 for the first hour for buses

        
        costPerHour.put("Bike", 10);   // Rs. 10 for each additional hour for bikes
        costPerHour.put("Car", 20);    // Rs. 20 for each additional hour for cars
        costPerHour.put("Truck", 30);   // Rs. 30 for each additional hour for trucks
        costPerHour.put("Bus", 30);     // Rs. 30 for each additional hour for buses
    }

    public int calculateCost(String vehicleType, long hours) {
    	
        int totalCost = initialCost.getOrDefault(vehicleType, 0);

        
        if (hours > 1) {
            totalCost += costPerHour.getOrDefault(vehicleType, 0) * (hours - 1);
        }

        return totalCost;
    }

    public void setCost(String vehicleType, int cost) {
        costPerHour.put(vehicleType, cost);
    }

    public void setInitialCost(String vehicleType, int cost) {
        initialCost.put(vehicleType, cost);
    }
}