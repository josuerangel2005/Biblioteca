package com.biblioteca.web.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "holacomoest4est4cl4v3eS7nhack34bl3jijijija";
  private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

  public String create(String username) {
    return JWT.create()
        .withSubject(username)
        .withIssuer("admin")
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
        .sign(ALGORITHM);
  }

  public boolean isValid(String jwt) {
    try {

      JWT.require(ALGORITHM)
          .build()
          .verify(jwt);
      return true;
    } catch (JWTVerificationException e) {
      return false;
    }
  }

  public String getUsernameFromToken(String jwt) {
    return JWT.require(ALGORITHM)
        .build()
        .verify(jwt)
        .getSubject();
  }
}
