package io.murad.blog.rest.controller;

import io.murad.blog.rest.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @PostMapping("/signup")
    public void registerUser(@RequestBody RegisterRequest registerRequest){

    }
}
