package se.lexicon.dao.impl;


import se.lexicon.dao.CustomerDao;
import se.lexicon.model.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    private List<Customer> final customers = new LinkedList<>();


    @Override
    public Customer create(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }
}
