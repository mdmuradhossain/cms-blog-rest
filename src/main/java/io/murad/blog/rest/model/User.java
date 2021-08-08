package io.murad.blog.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;

    @NotBlank(message="Username is required")
    private String userName;

    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    private String email;

    private Instant created;
    private boolean enabled;

}
