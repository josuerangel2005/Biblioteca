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

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.domain.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategory() {
    return new ResponseEntity<List<Category>>(this.categoryService.getAllsCategory(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategotyById(@PathVariable int id) {
    return new ResponseEntity<Category>(this.categoryService.getCategoryById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Category> saveCategory(@RequestBody CategorySave category) {
    return new ResponseEntity<Category>(this.categoryService.saveCategory(category), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@RequestBody CategorySave category, @PathVariable int id) {
    return new ResponseEntity<Category>(this.categoryService.updateCategory(category, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
    this.categoryService.deleteCategory(id);
    return ResponseEntity.ok().build();
  }

}
