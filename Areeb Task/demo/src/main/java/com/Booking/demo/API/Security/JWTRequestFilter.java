package com.Booking.demo.API.Security;

import com.Booking.demo.Model.Repository.UserRepo;
import com.Booking.demo.Model.User;
import com.Booking.demo.Service.JWTService;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserRepo userRepo;

    public JWTRequestFilter(JWTService jwtService, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);

            try {
                String username = jwtService.getUsername(token);


                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Optional<User> optionalUser = userRepo.findByUsernameIgnoreCase(username);


                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        CustomUserDetails userDetails = new CustomUserDetails(user);
                        System.out.println(userDetails.getAuthorities());
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }
                }

            } catch (JWTDecodeException e) {
                System.out.println("JWT Decode error: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}

