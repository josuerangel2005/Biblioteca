package com.biblioteca.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import com.biblioteca.persistence.audit.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@EntityListeners({ AuditableEntity.class })
public class UserEntity extends AuditableEntity {

  @Id
  @Column(unique = true, nullable = false, length = 150)
  private String username;

  @Column(nullable = false, length = 150)
  private String password;

  @Column(nullable = false)
  private Boolean disabled;

  @Column(nullable = false)
  private Boolean locked;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<UserRolEntity> roles = new HashSet<>();

  @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY)
  private Usuario usuario;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Set<UserRolEntity> getRoles() {
    return roles;
  }

  public void setRoles(Set<UserRolEntity> roles) {
    this.roles = roles;
  }
}
