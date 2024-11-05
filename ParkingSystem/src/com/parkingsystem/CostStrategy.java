package com.parkingsystem;

import java.util.HashMap;
import java.util.Map;

public class CostStrategy {
    private Map<String, Integer> costPerHour;

    public CostStrategy() {
        costPerHour = new HashMap<>();
        costPerHour.put("Bike", 10);
        costPerHour.put("Car", 20);
        costPerHour.put("Truck", 30);
        costPerHour.put("Bus", 30);
    }

    public int calculateCost(String vehicleType, int hours) {
        return costPerHour.getOrDefault(vehicleType, 0) * hours;
    }

    public void setCost(String vehicleType, int cost) {
        costPerHour.put(vehicleType, cost);
    }
}