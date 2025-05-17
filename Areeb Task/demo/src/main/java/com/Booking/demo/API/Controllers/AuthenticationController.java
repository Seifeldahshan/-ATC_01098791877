package com.Booking.demo.API.Controllers;

import com.Booking.demo.API.Security.CustomUserDetails;
import com.Booking.demo.DTO.LoginRequest;
import com.Booking.demo.DTO.LoginResponse;
import com.Booking.demo.DTO.RegisterRequest;
import com.Booking.demo.Exception.UserAlreadyExistException;
import com.Booking.demo.Model.User;
import com.Booking.demo.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity  completeRegistration(@Valid  @RequestBody RegisterRequest registerRequest) {
           try {
               return ResponseEntity.ok(authenticationService.register(registerRequest));
           } catch (UserAlreadyExistException e) {
               return ResponseEntity.status(HttpStatus.CONFLICT).build();
           }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse>  login(@Valid @RequestBody LoginRequest loginRequest) {

        String token = authenticationService.loginUser(loginRequest);

        if (token == null) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setFailureReason("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setToken(token);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return customUserDetails.getUser();
    }
}
