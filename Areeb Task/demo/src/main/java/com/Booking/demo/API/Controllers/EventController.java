package com.Booking.demo.API.Controllers;

import com.Booking.demo.Model.Event;
import com.Booking.demo.Service.EventService;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/getEvents")
    public Page<Event> getEvents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String category
    ) {
        if (category != null && !category.equals("All")) {
            return eventService.getEventsByCategoryName(category, page, size);
        } else {
            return eventService.getAllEvents(page, size);
        }
    }


    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }
}
