package com.e_commerce.demo.security.jwt;
//secretkey generator
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService{

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    //helper method
    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        return buildToken(extraClaims, userDetails);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {

        return Jwts.builder()

                .claims(extraClaims)

                .subject(userDetails.getUsername())

                .issuedAt(new Date(System.currentTimeMillis()))

                .expiration(
                        new Date(System.currentTimeMillis() + jwtExpiration)
                )

                .signWith(getSignInKey())

                .compact();
    }

    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSignInKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token)

                .before(new Date());
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username =
                extractUsername(token);

        return username.equals(userDetails.getUsername())

                && !isTokenExpired(token);
    }


}






















//JWT secret key generator code

//public static void main(String[] args) {
//    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    String secret = Base64.getEncoder().encodeToString(key.getEncoded());
//
//    System.out.println(secret);
//}