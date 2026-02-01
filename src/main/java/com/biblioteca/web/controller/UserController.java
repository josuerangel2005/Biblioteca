package com.biblioteca.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.User.User;
import com.biblioteca.domain.dto.User.UserSave;
import com.biblioteca.domain.service.UsuarioService;
import com.biblioteca.persistence.projection.Usuarios;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UsuarioService usuarioService;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return new ResponseEntity<List<User>>(this.usuarioService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable int id) {
    return new ResponseEntity<User>(this.usuarioService.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> save(@RequestBody UserSave userSave) {
    return new ResponseEntity<User>(this.usuarioService.save(userSave), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@RequestBody UserSave userSave, @PathVariable int id) {
    return new ResponseEntity<User>(this.usuarioService.update(userSave, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    this.usuarioService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/topusers")
  public ResponseEntity<List<Usuarios>> getTop3Users(@RequestParam LocalDateTime date1,
      @RequestParam LocalDateTime date2) {
    return ResponseEntity.ok(this.usuarioService.getTop3Users(date1, date2));
  }
}
