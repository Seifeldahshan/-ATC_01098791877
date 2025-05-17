package com.Booking.demo.Model.Repository;

import com.Booking.demo.Model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
}
