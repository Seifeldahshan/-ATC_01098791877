package com.Booking.demo.Service;

import com.Booking.demo.Exception.EventNotFoundException;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.Repository.EventRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class EventService {

    private final EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Page<Event> getEventsByCategoryName(String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepo.findByCategory_NameIgnoreCase(categoryName, pageable);
    }

    public Page<Event> getAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepo.findAll(pageable);
    }
    public Event getEventById(Long eventId) {
        return eventRepo.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException("Event not found"));
    }
}
