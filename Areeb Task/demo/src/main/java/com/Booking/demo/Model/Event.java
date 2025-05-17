package com.Booking.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    @Column(name = "startTime")
    private LocalTime startTime;
    @Column(name = "venue")
    private String venue;
    @Column(name = "price")
    private Long price;
    @Column(name = "image")
    private String image;


    @OneToMany(mappedBy = "event" ,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List <Booking> booking;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Event(Long id, String name, String description, Date date, String venue, Long price, String image, Category category, List<Booking> booking) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.price = price;
        this.image = image;
        this.category = category;
        this.booking = booking;

    }

    public Event() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalTime  getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime  startTime) {
        this.startTime = startTime;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
