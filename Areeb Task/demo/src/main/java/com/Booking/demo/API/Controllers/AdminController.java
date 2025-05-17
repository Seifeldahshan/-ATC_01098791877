package com.Booking.demo.API.Controllers;

import com.Booking.demo.API.Security.CustomUserDetails;
import com.Booking.demo.DTO.AddEventRequest;
import com.Booking.demo.DTO.UpdateEventRequest;
import com.Booking.demo.Model.Category;
import com.Booking.demo.Model.Event;
import com.Booking.demo.Model.User;
import com.Booking.demo.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getEvents")
    public List<Event> getEvents() {
        return adminService.getAllEvents();
    }

    @PostMapping(value = "/addEvents" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addEvent(@Valid @RequestPart AddEventRequest eventRequest , @RequestPart  MultipartFile file) {

        System.out.println("Received eventRequest: " + eventRequest);
        System.out.println("Received file: " + file.getOriginalFilename());
        adminService.addEvent(eventRequest, file);

        return ResponseEntity.ok("Event added");
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        adminService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted");
    }

    @GetMapping("/getEvent/{eventId}")
    public ResponseEntity<Event> getEvent( @PathVariable Long eventId) {
        return ResponseEntity.ok(adminService.getEvent(eventId));
    }

    @PutMapping(value = "/updateEvent/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateEvent(@PathVariable Long id,@RequestPart UpdateEventRequest eventRequest , @RequestPart (required = false)  MultipartFile file) {
        eventRequest.setId(id);
        adminService.updateEvent( eventRequest, file);
        return ResponseEntity.ok("Event updated");
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getCategories() {
      return  ResponseEntity.ok(adminService.getCategories());
    }
    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestBody String name ) {
        String response = adminService.addCategory(name);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteCategory/{CategoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long CategoryId) {
        adminService.deleteCategory(CategoryId);
        return ResponseEntity.ok("Category deleted");
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return customUserDetails.getUser();
    }


}
