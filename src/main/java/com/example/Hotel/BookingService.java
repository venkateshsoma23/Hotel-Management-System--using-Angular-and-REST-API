package com.example.Hotel;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

@Service
public class BookingService {

    private final BookingRepository repo;

    private final int TOTAL_AC = 50;
    private final int TOTAL_NON_AC = 50;
    private final Set<Integer> bookedAcRooms = new HashSet<>();
    private final Set<Integer> bookedNonAcRooms = new HashSet<>();
    private final AtomicInteger acCounter = new AtomicInteger(10); // starting room numbers, adjust as needed
    private final AtomicInteger nonAcCounter = new AtomicInteger(60);

    public BookingService(BookingRepository repo) {
        this.repo = repo;
        
    }

    @PostConstruct
    @Transactional
    public void initRoomSets() {
        rebuildRoomSets();
    }

    private void rebuildRoomSets() {
        bookedAcRooms.clear();
        bookedNonAcRooms.clear();
        for (Booking b : repo.findAll()) {
            if (b.getRoomType().equalsIgnoreCase("AC")) {
                bookedAcRooms.addAll(b.getBookedRooms());
            } else if (b.getRoomType().equalsIgnoreCase("Non-AC")) {
                bookedNonAcRooms.addAll(b.getBookedRooms());
            }
        }
    }

    public List<Booking> getAll() {
        return repo.findAll();
    }

    public Booking book(String customerName, String contact, String roomType, int count) {
        List<Integer> assigned = new ArrayList<>();
        if (roomType.equalsIgnoreCase("AC")) {
            int available = TOTAL_AC - bookedAcRooms.size();
            if (count > available) throw new RuntimeException("Not enough AC rooms available");
            while (assigned.size() < count) {
                int roomNum = acCounter.getAndIncrement();
                if (!bookedAcRooms.contains(roomNum)) {
                    bookedAcRooms.add(roomNum);
                    assigned.add(roomNum);
                }
            }
        } else if (roomType.equalsIgnoreCase("Non-AC")) {
            int available = TOTAL_NON_AC - bookedNonAcRooms.size();
            if (count > available) throw new RuntimeException("Not enough Non-AC rooms available");
            while (assigned.size() < count) {
                int roomNum = nonAcCounter.getAndIncrement();
                if (!bookedNonAcRooms.contains(roomNum)) {
                    bookedNonAcRooms.add(roomNum);
                    assigned.add(roomNum);
                }
            }
        } else {
            throw new RuntimeException("Unknown room type");
        }

        Booking b = new Booking(customerName, contact, roomType, count, assigned);
        return repo.save(b);
    }

    public Map<String, Object> getAvailability(String type) {
        Map<String, Object> m = new HashMap<>();
        if (type.equalsIgnoreCase("AC")) {
            int available = TOTAL_AC - bookedAcRooms.size();
            m.put("type", "AC");
            m.put("total", TOTAL_AC);
            m.put("available", available);
        } else if (type.equalsIgnoreCase("Non-AC")) {
            int available = TOTAL_NON_AC - bookedNonAcRooms.size();
            m.put("type", "Non-AC");
            m.put("total", TOTAL_NON_AC);
            m.put("available", available);
        } else {
            throw new RuntimeException("Unknown room type");
        }
        return m;
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = repo.findById(bookingId).orElse(null);
        if (booking == null) return;
        
        if (booking.getRoomType().equalsIgnoreCase("AC")) {
            bookedAcRooms.removeAll(booking.getBookedRooms());
        } else if (booking.getRoomType().equalsIgnoreCase("Non-AC")) {
            bookedNonAcRooms.removeAll(booking.getBookedRooms());
        }
        repo.deleteById(bookingId);
    
        rebuildRoomSets();
    }
}
