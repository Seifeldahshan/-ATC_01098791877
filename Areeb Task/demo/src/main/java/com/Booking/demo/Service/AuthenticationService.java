package com.Booking.demo.Service;


import com.Booking.demo.DTO.LoginRequest;
import com.Booking.demo.DTO.RegisterRequest;
import com.Booking.demo.Exception.UserAlreadyExistException;
import com.Booking.demo.Model.Repository.UserRepo;
import com.Booking.demo.Model.Repository.VerificationTokenRepo;
import com.Booking.demo.Model.User;
import com.Booking.demo.Model.VerificationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepo userRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private VerificationTokenRepo verificationTokenRepository;


    public AuthenticationService(UserRepo userRepository, EncryptionService encryptionService, JWTService jwtService, VerificationTokenRepo verificationTokenRepository) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public User register(RegisterRequest registerRequest) throws UserAlreadyExistException {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encryptionService.encryptPassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole("USER");
        userRepository.save(user);

        VerificationToken verificationToken = createVerificationToken(user);
        verificationTokenRepository.save(verificationToken);
        return user ;
    }

    public String loginUser(LoginRequest loginRequest){
        Optional<User> opUser = userRepository.findByEmail(loginRequest.getEmail());
        if (opUser.isPresent() ) {
            User user = opUser.get();
            if (encryptionService.checkPassword(loginRequest.getPassword(), user.getPassword())) {
                    return jwtService.generateToken(user);
            }
        }
        return null;
    }

    private VerificationToken createVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateToken(user));
        verificationToken.setUser(user);
        verificationToken.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }


}