package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Customer model.
 * <p>
 * This test suite verifies the creation of a Customer instance and the behavior
 * of setters when assigning valid and invalid values. The goal is to ensure
 * that the Customer class enforces correct data handling and validation.
 */
class CustomerTest {

    private Customer testObject;

    /**
     * Runs before each test method.
     * Scenario:
     * - Prepare a fresh Customer object so test cases do not affect each other.
     */
    @BeforeEach
    void setUp() {
        // TODO: Initialize testObject with a default Customer instance
    }

    /**
     * Scenario:
     * - Ensure that a new Customer object can be created successfully.
     * - Confirm that fields are assigned or initialized as expected.
     */
    @Test
    void shouldCreateCustomerSuccessfully() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Assign a valid name to the customer.
     * - Verify that the name is stored correctly.
     */
    @Test
    void shouldSetNameWhenNameIsValid() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Attempt to set an invalid name (e.g., null or empty).
     * - The Customer class should prevent this and possibly throw an exception.
     */
    @Test
    void shouldNotSetNameWhenNameIsInvalid() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Assign a valid phone number to the customer.
     * - Verify the number is stored correctly.
     */
    @Test
    void shouldSetPhoneNumberWhenValid() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Attempt to assign an invalid phone number (e.g., improperly formatted).
     * - The Customer class should prevent this or indicate an error.
     */
    @Test
    void shouldNotSetPhoneNumberWhenInvalid() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Assign a valid ID to the customer (e.g., positive number).
     * - Ensure the ID is updated correctly.
     */
    @Test
    void shouldSetIdWhenValid() {
        // TODO: Arrange, Act, Assert
    }

    /**
     * Scenario:
     * - Assign a valid vehicle plate number to the customer.
     * - Verify that it is stored and retrievable.
     */
    @Test
    void shouldSetVehiclePlateNumberSuccessfully() {
        // TODO: Arrange, Act, Assert
    }
}
