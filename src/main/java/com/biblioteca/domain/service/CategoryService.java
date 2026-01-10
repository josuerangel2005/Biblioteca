package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.domain.repository.CategoryRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAllsCategory() {
    return this.categoryRepository.getAllsCategory();
  }

  public Category getCategoryById(int id) {
    return this.categoryRepository.getCategoryById(id);
  }

  public Category saveCategory(CategorySave category) {
    return this.categoryRepository.saveCategory(category);
  }

  public Category updateCategory(CategorySave category, int id) {
    return this.categoryRepository.updateCategory(category, id);
  }

  public void deleteCategory(int id) {
    this.categoryRepository.deleteCategoryById(id);
  }
}
