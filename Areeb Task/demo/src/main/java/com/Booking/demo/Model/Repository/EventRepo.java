package com.Booking.demo.Model.Repository;

import com.Booking.demo.DTO.EventDTO;
import com.Booking.demo.Model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {
Optional<Event> findById(Long id);
    Page<Event> findByCategory_NameIgnoreCase(String name, Pageable pageable);


}
