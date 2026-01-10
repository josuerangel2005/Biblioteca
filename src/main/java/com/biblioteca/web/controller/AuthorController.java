package com.biblioteca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.Author.AuthorResponse;
import com.biblioteca.domain.dto.Author.AuthorSave;
import com.biblioteca.domain.dto.Author.AuthorUpdate;
import com.biblioteca.domain.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping
  public ResponseEntity<List<AuthorResponse>> getAllAuthor() {
    return new ResponseEntity<List<AuthorResponse>>(this.authorService.getAllAuthor(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable int id) {
    return new ResponseEntity<AuthorResponse>(this.authorService.getAuthorById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AuthorResponse> saveAuthor(@RequestBody AuthorSave authorSave) {
    return new ResponseEntity<AuthorResponse>(this.authorService.saveAuthor(authorSave), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable int id, @RequestBody AuthorUpdate authorUpdate) {
    return new ResponseEntity<AuthorResponse>(this.authorService.updateAuthor(id, authorUpdate), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
    this.authorService.deleteAuthor(id);
    return ResponseEntity.ok().build();
  }
}
