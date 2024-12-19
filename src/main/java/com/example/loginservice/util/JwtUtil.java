package com.example.loginservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "TzUwsEvGnlBVqDaXUqJQWLtKDyZUcoccWYLL3pKbUn4=y"; // Use a strong key
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId) // Add userId as a claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) &&
                !extractAllClaims(token).getExpiration().before(new Date()));
    }
}

