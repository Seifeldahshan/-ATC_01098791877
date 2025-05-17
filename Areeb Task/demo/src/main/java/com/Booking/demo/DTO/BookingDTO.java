package com.Booking.demo.DTO;

import java.time.LocalTime;

public class BookingDTO {
    private Long id;
    private LocalTime bookingDate;
    private UserDTO user;
    private EventRequest eventRequest;

    public BookingDTO(LocalTime bookingDate, Long id, UserDTO user, EventRequest eventRequest) {
        this.bookingDate = bookingDate;
        this.id = id;
        this.user = user;
        this.eventRequest = eventRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EventRequest getEventRequest() {
        return eventRequest;
    }

    public void setEventRequest(EventRequest eventRequest) {
        this.eventRequest = eventRequest;
    }
}
