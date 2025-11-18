package se.lexicon.service;

import se.lexicon.dao.*;
import se.lexicon.dao.impl.*;
import se.lexicon.model.*;

import java.util.Optional;

public class CustomerService {
    CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer registerCustomer(String name, String phoneNumber, String plateNumber) {
        try {
            Customer customer = new Customer(name, phoneNumber, plateNumber);
            return customerDao.create(customer);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public Customer getCustomer(int id) {
        Optional<Customer> customer = customerDao.findById(id);
        return customer.orElseThrow();
    }
}
