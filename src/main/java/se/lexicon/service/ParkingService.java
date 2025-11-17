package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ParkingService {
    CustomerDao customerDao = new CustomerDaoImpl();
    ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();
    ReservationDao reservationDao = new ReservationDaoImpl();

    public Customer registerCustomer(String name, String phoneNumber, String plateNumber) {
        try {
            Customer customer = new Customer(name, phoneNumber, plateNumber);
            return customerDao.create(customer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotDao.findAll();
    }

    public Customer getCustomer(int id) {
        Optional<Customer> customer = customerDao.findById(id);
        return customer.orElseThrow();
    }

    public ParkingSpot reserveSpot(Customer customer, LocalDateTime startTime, LocalDateTime endTime, Integer areaCode, Integer spotNumber) {
        return null;
    }

    public void vacate(Customer customer) {

    }
}
