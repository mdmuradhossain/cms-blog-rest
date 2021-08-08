package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.RegisterRequest;
import io.murad.blog.rest.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }
}
