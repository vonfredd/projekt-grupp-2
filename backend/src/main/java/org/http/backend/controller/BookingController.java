package org.http.backend.controller;

import org.http.backend.entity.Booking;
import org.http.backend.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    ResponseEntity<List<Booking>> all() {
        return ResponseEntity.ok().body(bookingService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> one(@PathVariable(name="id") String id) {
        return ResponseEntity.ok().body(bookingService.one(id));
    }


    @PostMapping
    public ResponseEntity<Booking> save(@RequestBody Booking booking) {
        try {
            return ResponseEntity.ok().body(bookingService.save(booking));
        } catch (Exception e) {
            e.printStackTrace();
            Booking emptyBooking = new Booking();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyBooking);
        }
    }

}
