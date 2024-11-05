package com.parkingsystem;

import java.util.Scanner;

public class Main {
    private static ParkingLot parkingLot;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initParkingLot(scanner);
        while (true) {
            System.out.println("1. Add Vehicle\n2. Remove Vehicle\n3. Check Availability\n4. Display Status\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addVehicle(scanner);
                    break;
                case 2:
                    removeVehicle(scanner);
                    break;
                case 3:
                    checkAvailability(scanner);
                    break;
                case 4:
                    displayStatus();
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    scanner.close(); 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initParkingLot(Scanner scanner) {
        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter number of bike spaces per floor: ");
        int bikeSpaces = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter number of car spaces per floor: ");
        int carSpaces = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter number of truck spaces per floor: ");
        int truckSpaces = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter number of bus spaces per floor: ");
        int busSpaces = scanner.nextInt();
        scanner.nextLine(); 
        parkingLot = new ParkingLot(floors, bikeSpaces, carSpaces, truckSpaces, busSpaces);
        System.out.println("Parking lot initialized successfully.");
    }

    private static void addVehicle(Scanner scanner) {
        System.out.print("Enter vehicle registration number: ");
        String regNumber = scanner.nextLine();
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine(); 
        System.out.print("Enter vehicle type (Bike/Car/Truck/Bus): ");
        String type = scanner.nextLine(); 
        String tokenId = generateTokenId(regNumber);
        Vehicle vehicle = new Vehicle(regNumber, color, type, tokenId);
        if (parkingLot.parkVehicle(vehicle)) {
            System.out.println("Vehicle parked successfully. Token ID: " + tokenId);
        } else {
            System.out.println("Parking lot is full. Cannot park the vehicle.");
        }
    }

    private static void removeVehicle(Scanner scanner) {
        System.out.print("Enter vehicle registration number to remove: ");
        String regNumber = scanner.nextLine(); // Use nextLine for string input
        Vehicle vehicle = parkingLot.removeVehicle(regNumber);
        
        // No need to check for cost here, as it is already handled in the ParkingLot class
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        }
    }

    private static void checkAvailability(Scanner scanner) {
        System.out.print("Enter vehicle type to check availability (Car/Truck/Bus): ");
        String type = scanner.nextLine(); 
        int availableSpaces = parkingLot.checkAvailability(type);
        System.out.println("Available spaces for " + type + ": " + availableSpaces);
    }

    private static void displayStatus() {
        parkingLot.displayStatus();
    }

    private static String generateTokenId(String regNumber) {
        return "TOKEN-" + regNumber + "-" + System.currentTimeMillis();
    }
}