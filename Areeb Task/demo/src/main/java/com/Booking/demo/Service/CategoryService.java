package com.Booking.demo.Service;

import com.Booking.demo.DTO.CategoryRequestDTO;
import com.Booking.demo.DTO.EventRequest;
import com.Booking.demo.Model.Category;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    @Value("${app.imageBaseUrl}")
    private String imageBaseUrl;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<CategoryRequestDTO> getAllCategoriesWithEvents() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryRequestDTO> categoryDTOs = new ArrayList<>();

        for (Category category : categories) {
            List<EventRequest> eventDTOs = new ArrayList<>();
            for (Event event : category.getEvents()) {
                EventRequest eventRequest = new EventRequest();

                eventRequest.setImageUrl(imageBaseUrl + event.getImage());
                eventRequest.setId(event.getId());
                eventRequest.setName(event.getName());
                eventRequest.setDescription(event.getDescription());
                eventRequest.setDate(event.getDate());
                eventRequest.setStartTime(event.getStartTime());
                eventRequest.setVenue(event.getVenue());
                eventRequest.setPrice(event.getPrice());
                eventRequest.setCategoryId(category.getId());

                eventDTOs.add(eventRequest);
            }

            CategoryRequestDTO categoryDTO = new CategoryRequestDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTO.setEvents(eventDTOs);

            categoryDTOs.add(categoryDTO);
        }

        return categoryDTOs;
    }
}
