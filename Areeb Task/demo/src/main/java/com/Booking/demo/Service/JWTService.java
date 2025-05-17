package com.Booking.demo.Service;
import com.Booking.demo.Model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String token;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;

    private static final String USERNAME_KEY = "username";
    private static final String ROLE = "role";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(token);
    }

    public String generateToken(User user) {
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withClaim(ROLE, user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }
    public String getRole(String token) {

        return JWT.decode(token).getClaim(ROLE).asString();
    }

}
