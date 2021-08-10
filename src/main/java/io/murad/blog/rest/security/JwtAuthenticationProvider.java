package io.murad.blog.rest.security;

import io.jsonwebtoken.Jwts;
import io.murad.blog.rest.exception.BlogRestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.*;
import java.sql.Date;
import java.time.Instant;

@Service
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationProvider {

    private final KeyStore keyStore;

    @Value("900000")
    private final Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("cms_blog", "secret".toCharArray());
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new BlogRestException("Exception occurred while retrieving public key from keystore.");
        }
    }

        public Long getJwtExpirationInMillis () {
            return jwtExpirationInMillis;
        }
    }