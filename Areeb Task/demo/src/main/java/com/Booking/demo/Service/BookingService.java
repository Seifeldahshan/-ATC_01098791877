package com.Booking.demo.Service;

import com.Booking.demo.API.Security.CustomUserDetails;
import com.Booking.demo.DTO.BookingDTO;
import com.Booking.demo.DTO.EventRequest;
import com.Booking.demo.DTO.UserDTO;
import com.Booking.demo.Exception.EventNotFoundException;
import com.Booking.demo.Model.Booking;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.Repository.BookingRepo;
import com.Booking.demo.Model.Repository.EventRepo;
import com.Booking.demo.Model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private EventRepo eventRepo;
    private BookingRepo bookingRepo ;

    public BookingService(EventRepo eventRepo, BookingRepo bookingRepo) {
        this.eventRepo = eventRepo;
        this.bookingRepo = bookingRepo;
    }

    public String bookingEvent(@AuthenticationPrincipal CustomUserDetails userDetails , Long eventId) {

        User user = userDetails.getUser();

        Event  event = eventRepo.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));;

        boolean alreadyBooked = bookingRepo.existsByUserAndEvent(user, event);
        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this event.");
        }

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(user);
        booking.setBookingDate(event.getStartTime());
        bookingRepo.save(booking);
        return "Event booked successfully";

    }
    public List<Long> getBookedEventIdsByUser(User user) {
        List<Booking> bookings = bookingRepo.findByUser(user);
        List<Long> bookedEventIds = new ArrayList<>();

        for (Booking booking : bookings) {
            bookedEventIds.add(booking.getEvent().getId());
        }

        return bookedEventIds;
    }


    public List<BookingDTO> getBookingsForUser(User user) {
        List<Booking> bookings = bookingRepo.findByUser(user);

        return bookings.stream().map(booking -> {
            Event event = booking.getEvent();

            EventRequest eventRequest = new EventRequest(
                    event.getId(),
                    event.getName(),
                    event.getDescription(),
                    event.getDate(),
                    event.getStartTime(),
                    event.getVenue(),
                    event.getPrice(),
                    event.getCategory() != null ? event.getCategory().getId() : null
            );

            return new BookingDTO(

                    booking.getBookingDate(),
                    booking.getId(),
                    new UserDTO(user.getId(), user.getUsername(), user.getEmail()),
                    eventRequest
            );
        }).toList();
    }

    public boolean cancelBooking(String username , Long eventId) {
        Optional<Booking> bookingOpt = bookingRepo.findByUserUsernameAndEventId(username, eventId);
        if (bookingOpt.isPresent()) {
            bookingRepo.delete(bookingOpt.get());
            return true;
        }
        return false;
    }

    public boolean isBooked(String username, Long eventId) {
        return bookingRepo.existsByUserUsernameAndEventId(username, eventId);
    }


}
