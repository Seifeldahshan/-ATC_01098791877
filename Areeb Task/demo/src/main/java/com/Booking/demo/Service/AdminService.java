package com.Booking.demo.Service;

import com.Booking.demo.DTO.*;
import com.Booking.demo.Exception.EventNotFoundException;
import com.Booking.demo.Model.Booking;
import com.Booking.demo.Model.Category;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.Repository.BookingRepo;
import com.Booking.demo.Model.Repository.CategoryRepo;
import com.Booking.demo.Model.Repository.EventRepo;

import java.awt.print.Pageable;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final EventRepo eventRepo;
    private final CategoryRepo categoryRepo;
    private final BookingRepo bookingRepo;

    public AdminService(EventRepo eventRepo, CategoryRepo categoryRepo, BookingRepo bookingRepo) {
        this.eventRepo = eventRepo;
        this.categoryRepo = categoryRepo;
        this.bookingRepo = bookingRepo;
    }

    public String addEvent(AddEventRequest eventRequest , MultipartFile image) {

        String imageName =saveImage(image);
        Category category = categoryRepo.findById(eventRequest.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not found"));

      Event event = new Event();
      event.setName(eventRequest.getName());
      event.setDescription(eventRequest.getDescription());
      event.setDate(eventRequest.getDate());
      event.setPrice(eventRequest.getPrice());
      event.setCategory(category);
      event.setVenue(eventRequest.getVenue());
      event.setStartTime(eventRequest.getStartTime());
      event.setImage( imageName);

        eventRepo.save(event);
      return "Event added successfully";

    }

    public void deleteEvent(Long eventId) {
        if (!eventRepo.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }
        eventRepo.deleteById(eventId);
    }

    public Event getEvent(Long eventId) {

     eventRepo.findById(eventId)
            .orElseThrow(()-> new DataIntegrityViolationException("Event not found"));

        return eventRepo.findById(eventId).get();
    }

    public String updateEvent(UpdateEventRequest eventRequest , MultipartFile image) {
       Event event = eventRepo.findById(eventRequest.getId())
                .orElseThrow(()-> new EventNotFoundException("Event not found"));

        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setPrice(eventRequest.getPrice());
        event.setVenue(eventRequest.getVenue());
        event.setStartTime(eventRequest.getStartTime());

        if (image != null  &&!image.isEmpty() ) {
            String imageName =saveImage(image) ;
            event.setImage(imageName);
        }

        eventRepo.save(event);
        return "Event updated successfully";
    }



    private String saveImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new RuntimeException("Image is empty");
        }
        try{
            String originalFilename = image.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString()+fileExtension ;

            Path uploadPath = Paths.get("upload");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath , StandardCopyOption.REPLACE_EXISTING);

            return fileName ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> getCategories(){
      return  categoryRepo.findAll();
    }

    public void deleteCategory(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepo.deleteById(categoryId);
    }


    public String addCategory(String name) {
        if (categoryRepo.existsByName(name)) {
            return "Category already exists!";
        }
        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);
        return "Category added successfully!";
    }

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }


}
