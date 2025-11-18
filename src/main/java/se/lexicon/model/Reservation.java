package se.lexicon.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private String reservationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private ParkingSpot parkingSpot;
    private Customer customer;


    public Reservation(String reservationId, LocalDateTime startTime, LocalDateTime endTime, Status status, ParkingSpot parkingSpot, Customer customer) {
        this.reservationId = reservationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.parkingSpot = parkingSpot;
        this.customer = customer;
    }


    public void setReservationId(String reservationId) {
        this.reservationId = UUID.randomUUID().toString();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    // TODO: Add toString() if needed


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