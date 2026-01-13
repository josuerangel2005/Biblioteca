package com.biblioteca.domain.repository;

import java.util.List;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.domain.dto.User.UserSave;

public interface UserRepository {
  List<User> getAll();

  User findById(int id);

  User save(UserSave userSave);

  User update(UserSave userSave, int id);

  void delete(int id);
}
