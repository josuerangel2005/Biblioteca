package com.biblioteca.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.persistence.projection.Usuarios;
import com.biblioteca.persistence.repository.UsuarioRepository;

@Service
public class UsuarioService {
  @Autowired
  private UsuarioRepository usuarioRepository;

  @Transactional(readOnly = true)
  public List<User> getAll() {
    return this.usuarioRepository.getAll();
  }

  @Transactional(readOnly = true)
  public User findById(int id) {
    return this.usuarioRepository.findById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public User save(UserSave userSave) {
    return this.usuarioRepository.save(userSave);
  }

  @Transactional(rollbackFor = Exception.class)
  public User update(UserSave userSave, int id) {
    return this.usuarioRepository.update(userSave, id);
  }

  @Transactional(rollbackFor = Exception.class)
  public void delete(int id) {
    this.usuarioRepository.delete(id);
  }

  @Transactional(readOnly = true)
  public List<Usuarios> getTop3Users(LocalDateTime date1, LocalDateTime date2) {
    return this.usuarioRepository.getTop3Users(date1, date2);
  }
}
