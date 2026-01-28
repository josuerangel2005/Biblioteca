package com.biblioteca.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biblioteca.persistence.crud.UserCrudRepository;
import com.biblioteca.persistence.entity.UserEntity;

@Service
public class UserSecurityService implements UserDetailsService {
  @Autowired
  private UserCrudRepository userCrudRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = this.userCrudRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario con username " + username + " no encontrado"));

    return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities(this.getAuthorities(userEntity.getRoles().stream().map(rol -> rol.getRol()).toList()))
        .disabled(userEntity.getDisabled())
        .accountLocked(userEntity.getLocked())
        .build();
  }

  public List<GrantedAuthority> getAuthorities(List<String> roles) {
    List<GrantedAuthority> authorities = roles.stream().map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
        .collect(Collectors.toList());

    return authorities;
  }

}
