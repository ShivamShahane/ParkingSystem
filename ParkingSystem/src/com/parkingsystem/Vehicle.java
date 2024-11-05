package com.parkingsystem;

import java.time.LocalDateTime;

public class Vehicle {
    private String registrationNumber;
    private String color;
    private String type;
    private String tokenId;
    private LocalDateTime entryTime; // New field to store entry time

    public Vehicle(String registrationNumber, String color, String type, String tokenId) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
        this.tokenId = tokenId;
        this.entryTime = LocalDateTime.now(); // Set entry time to now
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getTokenId() {
        return tokenId;
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}