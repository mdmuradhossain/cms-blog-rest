package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.RegisterRequest;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
    }
}
