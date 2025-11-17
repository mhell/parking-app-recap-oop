package se.lexicon.model;

import se.lexicon.dao.sequencer.CustomerIdSequencer;

import java.util.Objects;

/**
 * Represents a customer who uses the parking reservation system.
 * <p>
 * Each customer has a unique ID, personal contact information,
 * and the vehicle's plate number associated with them.
 * <p>
 * This class is used when creating reservations and managing
 * parking activity in the application.
 */
public class Customer {

    // Fields
    private Integer id;
    private String name;
    private String phoneNumber;
    private String vehiclePlateNumber;

    // TODO: Add constructors

    public Customer(String name, String phoneNumber, String vehiclePlateNumber) {
        this.id = CustomerIdSequencer.nextId();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehiclePlateNumber = vehiclePlateNumber;
    }


    // TODO: Add getters and setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }


    // TODO: Add toString() method if needed


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
