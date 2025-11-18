package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;

import java.util.Optional;

import se.lexicon.dao.sequencer.CustomerIdSequencer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for CustomerDaoImpl.
 * <p>
 * These tests verify that customer data is stored, retrieved,
 * and managed correctly in the DAO implementation.
 * <p>
 * All test logic is intentionally left as TODO so students can implement the tests.
 */
class CustomerDaoImplTest {

    private CustomerDaoImpl customerDao;

    /**
     * Runs before each test.
     * Scenario:
     * - Create a new instance of the DAO so each test starts clean.
     */
    @BeforeEach
    void setUp() {
        customerDao = new CustomerDaoImpl();
        CustomerIdSequencer.reset();

        // TODO: Initialize testObject before each test
    }

    /**
     * Scenario:
     * - Add a new customer to the DAO.
     * - Ensure the customer can be retrieved afterward.
     */
    @Test
    void shouldCreateAndStoreCustomerSuccessfully() {

        // Arrange
        Customer customer = new Customer("Alex", "123456", "ABC123");

        // Act
        Customer created = customerDao.create(customer);

        // Assert
        assertNotNull(created);
        assertEquals(customer, created);
        assertTrue(created.getId() > 0);

        Optional<Customer> result = customerDao.findById(created.getId());
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());

    }

    /**
     * Scenario:
     * - Retrieve all stored customers.
     * - Verify that the list contains the expected number of customers.
     */
    @Test
    void shouldReturnAllCustomers() {

        // Arrange
        Customer customer = new Customer("Bob", "777777", "BBC111");
        customerDao.create(customer);

        // Act
        Optional<Customer> result = customerDao.findById(customer.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());

        // Also test a nonexisting ID
        Optional<Customer> emptyResult = customerDao.findById(999);
        assertTrue(emptyResult.isEmpty());

    }

    /**
     * Scenario:
     * - Search for a customer by ID.
     * - If the customer exists, return it.
     * - If not, return an empty optional or null (depending on your design).
     */
    @Test
    void shouldFindCustomerById() {
        // Arrange
        Customer customer = new Customer("Bob", "777777", "BBC111");
        customerDao.create(customer);

        // Act
        Optional<Customer> result = customerDao.findById(customer.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());

        // Also test missing ID
        Optional<Customer> notFound = customerDao.findById(999);
        assertTrue(notFound.isEmpty());
    }

    /**
     * Scenario:
     * - Delete a customer from the DAO.
     * - Verify the customer is no longer retrievable.
     */
    /*
    @Test
    void shouldDeleteCustomerSuccessfully() {
        // TODO: Arrange, Act, Assert
    }
    */
}
