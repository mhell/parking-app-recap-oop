package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        testObject = new Customer("Alex", "123456", "ABC123");
    }

    /**
     * Scenario:
     * - Ensure that a new Customer object can be created successfully.
     * - Confirm that fields are assigned or initialized as expected.
     */
    @Test
    void shouldCreateCustomerSuccessfully() {
        // Arrange
        String name = "Bob";
        String phone = "777777";
        String plate = "XYZ111";

        // Act
        Customer customer = new Customer(name, phone, plate);

        // Assert
        assertNotNull(customer);
        assertEquals(name, customer.getName());
        assertEquals(phone, customer.getPhoneNumber());
        assertEquals(plate, customer.getVehiclePlateNumber());
        assertTrue(customer.getId() > 0);
    }

    /**
     * Scenario:
     * - Assign a valid name to the customer.
     * - Verify that the name is stored correctly.
     */
    @Test
    void shouldSetNameWhenNameIsValid() {
        // Arrange
        String newName = "Charlie";

        // Act
        testObject.setName(newName);

        // Assert
        assertEquals(newName, testObject.getName());
    }

    /**
     * Scenario:
     * - Attempt to set an invalid name (e.g., null or empty).
     * - The Customer class should prevent this and possibly throw an exception.
     */
    @Test
    void shouldNotSetNameWhenNameIsInvalid() {
        // Arrange
        String invalidName1 = null;
        String invalidName2 = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> testObject.setName(invalidName1));
        assertThrows(IllegalArgumentException.class, () -> testObject.setName(invalidName2));
    }

    /**
     * Scenario:
     * - Assign a valid phone number to the customer.
     * - Verify the number is stored correctly.
     */
    @Test
    void shouldSetPhoneNumberWhenValid() {
        // Arrange
        String newPhone = "99999999";

        // Act
        testObject.setPhoneNumber(newPhone);

        // Assert
        assertEquals(newPhone, testObject.getPhoneNumber());
    }

    /**
     * Scenario:
     * - Attempt to assign an invalid phone number (e.g., improperly formatted).
     * - The Customer class should prevent this or indicate an error.
     */
    @Test
    void shouldNotSetPhoneNumberWhenInvalid() {
        // Arrange
        String invalidphone1 = null;
        String invalidphone2 = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> testObject.setPhoneNumber(invalidphone1));
        assertThrows(IllegalArgumentException.class, () -> testObject.setPhoneNumber(invalidphone2));
    }

    /**
     * Scenario:
     * - Assign a valid vehicle plate number to the customer.
     * - Verify that it is stored and retrievable.
     */
    @Test
    void shouldSetVehiclePlateNumberSuccessfully() {
        // Arrange
        String newPlate = "ABC123";

        // Act
        testObject.setVehiclePlateNumber(newPlate);

        // Assert
        assertEquals(newPlate, testObject.getVehiclePlateNumber());
    }
}
