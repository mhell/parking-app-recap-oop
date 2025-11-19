package se.lexicon;

import se.lexicon.model.*;
import se.lexicon.service.*;

import java.util.List;
import java.util.Scanner;

// TODO: needs completions

public class ParkingApp {
    CustomerService customerService;
    ParkingService parkingService;
    ReservationService reservationService;
    VacateService vacateService;

    public ParkingApp(CustomerService customerService, ParkingService parkingService,
                      ReservationService reservationService, VacateService vacateService) {
        this.customerService = customerService;
        this.parkingService = parkingService;
        this.reservationService = reservationService;
        this.vacateService = vacateService;
    }

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
        String name = getInput("Enter full name: ");
        String phoneNumber = getInput("Enter phone number: ");
        String plateNumber = getInput("Enter plate number: ");
        Customer customer = customerService.registerCustomer(name, phoneNumber, plateNumber);
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
            int customerId = Integer.parseInt(getInput("Enter customer id: "));
            int duration = Integer.parseInt(getInput("Enter duration (hours): "));
            int areaCode = Integer.parseInt(getInput("Enter area code: "));
            int spotNumber = Integer.parseInt(getInput("Enter spot number: "));
            Reservation reservation = reservationService.reserveSpot(customerId, duration, areaCode, spotNumber);
            System.out.println("Successfully made reservation: " + reservation);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void vacateParkingSpot() {
        try {
            int areaCode = Integer.parseInt(getInput("Enter area code: "));
            int spotNumber = Integer.parseInt(getInput("Enter spot number: "));
            Reservation reservation = vacateService.vacateSpot(areaCode, spotNumber);
            System.out.println("Successfully vacated parking spot " + spotNumber + " with area code: " + areaCode +  " " + reservation);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
