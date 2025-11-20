package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.sequencer.CustomerIdSequencer;
import se.lexicon.model.Customer;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;
    private FakeCustomerDao fakeCustomerDao;

    @BeforeEach
    void setUp() {
        CustomerIdSequencer.reset();
        fakeCustomerDao = new FakeCustomerDao();
        customerService = new CustomerService(fakeCustomerDao);
    }

    class FakeCustomerDao implements CustomerDao {
        private Customer storedCustomer;

        @Override
        public Customer create(Customer customer) {
            this.storedCustomer = customer;
            return customer;
        }

        @Override
        public Optional<Customer> findById(int id) {
            if (storedCustomer != null && storedCustomer.getId() == id) {
                return Optional.of(storedCustomer);
            }
            return Optional.empty();
        }
    }

    @Test
    void shouldRegisterCustomerSuccessfully() {
        // Arrange
        String name = "Alex";
        String phone = "123456";
        String plate = "ABC123";

        // Act
        Customer result = customerService.registerCustomer(name, phone, plate);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(phone, result.getPhoneNumber());
        assertEquals(plate, result.getVehiclePlateNumber());
        assertTrue(result.getId() > 0);
    }

    @Test
    void shouldReturnNullWhenRegisterCustomerWithInvalidData() {
        // Arrange
        String invalidName = null;
        String phone = "123456";
        String plate = "ABC123";

        // Act
        Executable result = () -> customerService.registerCustomer(invalidName, phone, plate);

        // Assert
        assertThrows(IllegalArgumentException.class, result);
    }

    @Test
    void shouldReturnCustomerWhenIdExists() {
        // Arrange
        Customer stored = new Customer("Alex", "123456", "ABC123");
        fakeCustomerDao.create(stored);   // manually store customer in fake DAO
        int id = stored.getId();

        // Act
        Customer result = customerService.getCustomer(id);

        // Assert
        assertNotNull(result);
        assertEquals(stored, result);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        // Arrange
        int invalidId = 999;  // no customer stored with this ID

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> customerService.getCustomer(invalidId));
    }


}
