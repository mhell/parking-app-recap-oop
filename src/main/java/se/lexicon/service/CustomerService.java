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
        Customer customer = new Customer(name, phoneNumber, plateNumber);
        return customerDao.create(customer);
    }

    public Customer getCustomer(int id) {
        Optional<Customer> customer = customerDao.findById(id);
        return customer.orElseThrow();
    }
}
