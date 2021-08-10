package io.murad.blog.rest.service;

import io.murad.blog.rest.dto.AuthenticationRequest;
import io.murad.blog.rest.dto.AuthenticationResponse;
import io.murad.blog.rest.dto.RegisterRequest;
import io.murad.blog.rest.exception.BlogRestException;
import io.murad.blog.rest.model.NotificationEmail;
import io.murad.blog.rest.model.User;
import io.murad.blog.rest.model.VerificationToken;
import io.murad.blog.rest.repository.UserRepository;
import io.murad.blog.rest.repository.VerificationTokenRepository;
import io.murad.blog.rest.security.JwtAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your Account",
                user.getEmail(), "Thank you for signing up to CMS Blog, " +
                "please click on the below url to activate your account: " +
                "http://localhost:8080/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new BlogRestException("Invalid Token"));
        getUserAndEnableAccount(verificationToken.get());
    }

    @Transactional
    public void getUserAndEnableAccount(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUserName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new BlogRestException("User not found with name" + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authenticate);
    String token = jwtAuthenticationProvider.generateToken(authenticate);
    return new AuthenticationResponse(token,authenticationRequest.getUsername());
    }
}
