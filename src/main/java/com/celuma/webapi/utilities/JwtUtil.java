package com.celuma.webapi.utilities;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {
    
    // Hardcoded key => need to create one for requests
    private SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(String username) {
        return Jwts.builder()
            .subject(username)
            .signWith(secretKey)
            .compact();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && isTokenExpired(token);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
