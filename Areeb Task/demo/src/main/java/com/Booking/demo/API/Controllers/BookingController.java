package com.Booking.demo.API.Controllers;

import com.Booking.demo.API.Security.CustomUserDetails;
import com.Booking.demo.DTO.BookingDTO;
import com.Booking.demo.Model.User;
import com.Booking.demo.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/ticket")
    public ResponseEntity<String>  bookTicket(@AuthenticationPrincipal CustomUserDetails userDetails , @RequestParam Long eventId){
        if (eventId == null){
            return ResponseEntity.badRequest().body("Event id is null");
        }
        bookingService.bookingEvent(userDetails , eventId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingDTO>> getMyBookings(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<BookingDTO> bookings = bookingService.getBookingsForUser(user);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/user/booked-event-ids")
    public ResponseEntity<List<Long>> getBookedEventIds(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Long> bookedEventIds = bookingService.getBookedEventIdsByUser(user);
        return ResponseEntity.ok(bookedEventIds);
    }


    @DeleteMapping("/ticket")
    public ResponseEntity<String> cancelBooking(@RequestParam Long eventId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {

            boolean cancelled = bookingService.cancelBooking(customUserDetails.getUsername(), eventId);
            if (cancelled) {
                return ResponseEntity.ok("Booking cancelled successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel booking.");
        }
    }

    @GetMapping("/isBooked")
    public ResponseEntity<Boolean> isBooked(@RequestParam Long eventId,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {

            boolean booked = bookingService.isBooked(customUserDetails.getUsername(), eventId);
            return ResponseEntity.ok(booked);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


}
