package com.Booking.demo.DTO;

import java.util.List;

public class CategoryRequestDTO {
    private Long id;
    private String name;
    private List<EventRequest> events;

    public CategoryRequestDTO() {}
    public CategoryRequestDTO(Long id, String name, List<EventRequest> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EventRequest> getEvents() {
        return events;
    }

    public void setEvents(List<EventRequest> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
