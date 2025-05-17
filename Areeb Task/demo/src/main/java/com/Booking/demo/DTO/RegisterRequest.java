package com.Booking.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String Username ;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Password must be at least 6 characters long and contain at least one letter and one number")
    @Size(min = 6, max = 32)
    private String Password ;

    @NotBlank
    @Email
    private String Email ;

    public RegisterRequest() {}
    public RegisterRequest(String Username, String Password, String Email) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
