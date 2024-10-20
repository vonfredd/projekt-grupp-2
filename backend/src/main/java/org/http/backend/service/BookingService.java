package org.http.backend.service;

import org.http.backend.entity.Booking;
import org.http.backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> all() {
        return bookingRepository.findAll();
    }

    public Booking one(String id) {
       Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElseThrow(() -> new RuntimeException("No such ID " + id));
    }

    public List<Integer> getBookedSeatsByScheduleId(String scheduleId) {
        List<Booking> bookingsForSchedule = bookingRepository.findByScheduleId(scheduleId);
        List<Integer> bookedSeats = new ArrayList<>();
        for (Booking booking : bookingsForSchedule) {
            List<Integer> seatsInBooking = booking.getBookedSeats(); // Assuming this method exists
            bookedSeats.addAll(seatsInBooking); // Add all booked seats to the list
        }

        return bookedSeats;
    }
}
