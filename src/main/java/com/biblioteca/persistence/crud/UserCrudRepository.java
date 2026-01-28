package com.biblioteca.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.persistence.entity.UserEntity;

public interface UserCrudRepository extends CrudRepository<UserEntity, String> {

}
