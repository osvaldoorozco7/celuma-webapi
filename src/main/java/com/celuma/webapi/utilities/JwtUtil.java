package com.celuma.webapi.utilities;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {
    
    // Hardcoded key
    private String jwtSectret = "9D4D3BE5C3ED92AF12EB6B473E1B7CD6EB523736B6FC96A5AB5495D3A6";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSectret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
            .subject(username)
            .signWith(getSigningKey())
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
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
