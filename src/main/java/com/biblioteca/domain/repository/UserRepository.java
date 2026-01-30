package com.biblioteca.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.persistence.projection.Usuarios;

public interface UserRepository {
  List<User> getAll();

  User findById(int id);

  User save(UserSave userSave);

  User update(UserSave userSave, int id);

  void delete(int id);

  List<Usuarios> getTop3Users(LocalDateTime date1, LocalDateTime date2);
}
