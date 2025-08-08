package com.example.Hotel;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel_db")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/bookings")
    public List<Booking> allBookings() {
        return service.getAll();
    }

    @PostMapping("/bookings")
    public Booking bookRoom(@RequestBody Map<String, Object> payload) {
        String customerName = (String) payload.get("customerName");
        String contact = (String) payload.get("contact");
        String roomType = (String) payload.get("roomType");
        int count = ((Number) payload.get("count")).intValue();

        return service.book(customerName, contact, roomType, count);
    }

    @DeleteMapping("/bookings")
    public void deleteBooking(@RequestParam Long id) {
        service.deleteBooking(id);
    }

    @GetMapping("/rooms/availability")
    public Map<String, Object> availability(@RequestParam("type") String type) {
        return service.getAvailability(type);
    }

}
