package com.SurveyApp.userProfile.jwtTokens;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;

import java.security.SignatureException;
import java.util.Date;

import com.SurveyApp.userProfile.jwtTokens.services.JwtTokenService;

public class JwtToken {


    public static String generateLoginToken(long userId, String name, String email) throws InvalidKeyException, InvalidKeyException, SignatureException, java.security.InvalidKeyException {
        JwtTokenService service = new JwtTokenService();
        return Jwts.builder()
                .issuer("https://localhost:8080/")
                .subject(String.valueOf(userId))
                .expiration(new Date(System.currentTimeMillis() + 900000))
                .issuedAt(new Date(System.currentTimeMillis()))
                .notBefore(new Date(System.currentTimeMillis()))
                .signWith(service.loadPrivateKey()).compact();
    }

}

