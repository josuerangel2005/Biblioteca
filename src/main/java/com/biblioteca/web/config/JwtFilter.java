package com.biblioteca.web.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.biblioteca.domain.service.UserSecurityService;
import com.biblioteca.persistence.crud.UserCrudRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserSecurityService userSecurityService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String jwt = authorizationHeader.split(" ")[1].trim();

    if (!this.jwtUtil.isValid(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }

    String username = this.jwtUtil.getUsernameFromToken(jwt);

    User user = (User) this.userSecurityService.loadUserByUsername(username);

    UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
        user.getPassword(), user.getAuthorities());
    uToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(uToken);

    filterChain.doFilter(request, response);
  }

}
