package com.hw.bookstore.security.auth.jwt;

import com.hw.bookstore.domain.entity.User;
import com.hw.bookstore.property.AuthProperty;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final AuthProperty authProperties;

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .issuer(String.valueOf(user.getId()))
                .subject(user.getEmail())
                .claim("roles", user.getAuthoritiesNames())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()
                        + authProperties.expiration().toMillis()))
                .signWith(getSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecret())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }

        return false;
    }

    private SecretKey getSecret() {
        return Keys.hmacShaKeyFor(authProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

}
