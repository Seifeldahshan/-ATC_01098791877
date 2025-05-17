package com.Booking.demo.API.Controllers;

import com.Booking.demo.DTO.CategoryRequestDTO;
import com.Booking.demo.Service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<CategoryRequestDTO> getAllCategories() {
        return categoryService.getAllCategoriesWithEvents();
    }
}
