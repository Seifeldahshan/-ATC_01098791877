package com.Booking.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Event> events ;

    public Category(Long id, String name, List<Event> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }
    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
