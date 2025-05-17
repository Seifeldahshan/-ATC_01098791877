package com.Booking.demo.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookingDate")
    private LocalTime  bookingDate;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("bookings")
    private Event event;

    public Booking(Long id, LocalTime  bookingDate, User user, Event event) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.user = user;
        this.event = event;
    }

    public Booking() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalTime  bookingDate) {
        this.bookingDate = bookingDate;
    }
}
