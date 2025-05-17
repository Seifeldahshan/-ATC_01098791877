package com.Booking.demo.Model.Repository;

import com.Booking.demo.Model.Booking;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    boolean existsByUserAndEvent(User user, Event event);
    Optional<Booking> findByUserIdAndEventId(Long userId, Long eventId);
    List<Booking> findByUser(User user);
    Optional<Booking> findByUserUsernameAndEventId(String username, Long eventId);

    boolean existsByUserUsernameAndEventId (String username, Long eventId);
}
