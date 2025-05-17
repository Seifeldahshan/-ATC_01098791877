package com.Booking.demo.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalTime;
import java.util.Date;

public class AddEventRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotBlank(message = "venue is required")
    private String venue;

    @FutureOrPresent(message = "Date must be today or in the future")
    @NotNull(message = "Date is required")
    private Date date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or positive")
    private Long price;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public AddEventRequest(String name, String description, Date date, LocalTime startTime, String venue, Long price, Long categoryId) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.venue = venue;
        this.price = price;
        this.categoryId = categoryId;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
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
}
