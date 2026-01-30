package com.biblioteca.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.domain.exception.UserAlreadyExistsException;
import com.biblioteca.domain.exception.UserNotExistsException;
import com.biblioteca.domain.repository.UserRepository;
import com.biblioteca.persistence.crud.UsuarioCrudRepository;
import com.biblioteca.persistence.entity.Usuario;
import com.biblioteca.persistence.mapper.UserMapper;
import com.biblioteca.persistence.mapper.UserSaveMapper;
import com.biblioteca.persistence.mapper.UserUpdateMapper;
import com.biblioteca.persistence.projection.Usuarios;

@Repository
public class UsuarioRepository implements UserRepository {
  @Autowired
  private UsuarioCrudRepository usuarioCrudRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserSaveMapper userSaveMapper;

  @Autowired
  private UserUpdateMapper updateMapper;

  @Override
  public void delete(int id) {
    this.usuarioCrudRepository.findById(id).orElseThrow(() -> new UserNotExistsException(id));
    this.usuarioCrudRepository.deleteById(id);
  }

  @Override
  public User findById(int id) {
    return this.userMapper
        .toUser(this.usuarioCrudRepository.findById(id).orElseThrow(() -> new UserNotExistsException(id)));
  }

  @Override
  public List<User> getAll() {
    return this.userMapper.toUsers(this.usuarioCrudRepository.findAll());
  }

  @Override
  public User save(UserSave userSave) {
    if (this.usuarioCrudRepository.findFirstByNombre(userSave.name()) != null)
      throw new UserAlreadyExistsException(userSave.name());
    return this.userMapper.toUser(
        this.usuarioCrudRepository.save(this.usuarioCrudRepository.save(this.userSaveMapper.toUsuario(userSave))));
  }

  @Override
  public User update(UserSave userSave, int id) {
    Usuario toUpdate = this.usuarioCrudRepository.findById(id).orElseThrow(() -> new UserNotExistsException(id));
    this.updateMapper.updateUsuarioFromUserSave(userSave, toUpdate);
    return this.userMapper.toUser(this.usuarioCrudRepository.save(toUpdate));
  }

  @Override
  public List<Usuarios> getTop3Users(LocalDateTime date1, LocalDateTime date2) {
    return this.usuarioCrudRepository.getTop3Users(date1, date2);
  }

}
