package com.biblioteca.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.AuthDto;
import com.biblioteca.web.config.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken uPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        authDto.username(), authDto.password());

    Authentication authentication = this.authenticationManager.authenticate(uPasswordAuthenticationToken);

    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, this.jwtUtil.create(authDto.username())).build();
  }
}
