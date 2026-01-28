package com.biblioteca.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_role")
@IdClass(UserRolEntityPK.class)
public class UserRolEntity {

  @Id
  @Column(nullable = false)
  private String username;

  @Id
  @Column(nullable = false)
  private String role;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
  private UserEntity user;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRol() {
    return role;
  }

  public void setRol(String role) {
    this.role = role;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

}
