package com.Booking.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "role", nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id desc")
    private List<VerificationToken> verificationTokens = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List <Booking> booking;

    public User(Long id, String username, String password, String email, String role, List<VerificationToken> verificationTokens, List<Booking> booking) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.verificationTokens = verificationTokens;
        this.booking = booking;
    }
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<VerificationToken> getVerificationTokens() {
        return verificationTokens;
    }

    public void setVerificationTokens(List<VerificationToken> verificationTokens) {
        this.verificationTokens = verificationTokens;
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }
}
