package se.lexicon;

import se.lexicon.model.*;
import se.lexicon.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ParkingApp {

    // TODO: needs completions

    CustomerService costumerService = new CustomerService();
    ParkingService parkingService = new ParkingService();
    ReservationService reservationService = new ReservationService();

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("""
                    1. Register Customer
                    2. Display Parking Spots
                    3. Reserve a Parking Spot
                    4. Vacate Parking Spot
                    5. Exit
                    """);

            String choice = getInput("Choose an option: ");
            switch (choice) {
                case "1" -> registerCustomer();
                case "2" -> displayParkingSpots();
                case "3" -> reserveParkingSpot();
                case "4" -> vacateParkingSpot();
                case "5" -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


    private void registerCustomer() {
        String name = getInput("Enter full name ");
        String phoneNumber = getInput("Enter phone number ");
        String plateNumber = getInput("Enter plate number ");
        Customer customer = costumerService.registerCustomer(name, phoneNumber, plateNumber);
        System.out.println("Successfully registered customer: " + customer);
    }

    private void displayParkingSpots() {
        List<ParkingSpot> parkingSpots = parkingService.getAllParkingSpots();
        System.out.println("Parking spots: ");
        for (ParkingSpot parkingSpot : parkingSpots) {
            System.out.println(parkingSpot);
        }
    }

    private void reserveParkingSpot() {
        try {
            Customer customer = costumerService.getCustomer(Integer.parseInt(getInput("Enter costumer id  ")));
            LocalDateTime startTime = LocalDateTime.parse(getInput("Enter start date (e.g. 2007-12-03T10:15:30: "));
            LocalDateTime endTime = LocalDateTime.parse(getInput("Enter start date (e.g. 2007-12-03T10:15:30: "));
            Integer areaCode = Integer.parseInt(getInput("Enter area code "));
            Integer spotNumber = Integer.parseInt(getInput("Enter spot number "));
            ParkingSpot parkingSpot = reservationService.reserveSpot(customer, startTime, endTime, areaCode, spotNumber);
            System.out.println("Successfully reserved parking spot " + parkingSpot);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void vacateParkingSpot() {
        try {
            Customer customer = costumerService.getCustomer(Integer.parseInt(getInput("Enter costumer id  ")));
            reservationService.vacate(customer);
            System.out.println("Successfully vacated parking spot for customer: " + customer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        new ParkingApp().start();
    }
}
