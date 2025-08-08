package com.example.Hotel;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String contact;
    private String roomType; 
    private int count;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> bookedRooms; 
    private Instant timestamp;

    public Booking() {}

    public Booking(String customerName, String contact, String roomType, int count, List<Integer> bookedRooms) {
        this.customerName = customerName;
        this.contact = contact;
        this.roomType = roomType;
        this.count = count;
        this.bookedRooms = bookedRooms;
        this.timestamp = Instant.now();
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContact() {
        return contact;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCount() {
        return count;
    }

    public List<Integer> getBookedRooms() {
        return bookedRooms;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setBookedRooms(List<Integer> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
