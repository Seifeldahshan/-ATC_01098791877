package com.Booking.demo.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;

import java.time.LocalTime;
import java.util.Date;

public class EventRequest {

    private Long id;
    private String name;

    private String description;

    private Date date;

    private LocalTime startTime;

    private String venue;

    private Long price;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;


    private Long categoryId;

public EventRequest() {}


    public EventRequest(Long id, String name, String description, Date date, LocalTime startTime, String venue, Long price,  Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.venue = venue;
        this.price = price;
        this.categoryId = categoryId;
    }

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

    public LocalTime  getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime  startTime) {
        this.startTime = startTime;
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


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

