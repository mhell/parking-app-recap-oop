package se.lexicon.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private final String reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private ParkingSpot parkingSpot;
    private Customer customer;

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, Status status, ParkingSpot parkingSpot, Customer customer) {
        this.reservationId = UUID.randomUUID().toString();
        setStartTime(startTime);
        setEndTime(endTime);
        setStatus(status);
        setParkingSpot(parkingSpot);
        setCustomer(customer);
    }

    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot cannot be null");
        }
        this.parkingSpot = parkingSpot;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", parkingSpot=" + parkingSpot +
                ", customer=" + customer +
                '}';
    }

    public void complete() {
        this.status = Status.COMPLETED;
    }

    public void setEndTimeByHours(int hours) {
        if (startTime != null) {
            this.endTime = startTime.plusHours(hours);
        }
    }

}