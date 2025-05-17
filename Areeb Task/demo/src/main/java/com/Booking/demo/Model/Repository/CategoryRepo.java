package com.Booking.demo.Model.Repository;

import com.Booking.demo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
