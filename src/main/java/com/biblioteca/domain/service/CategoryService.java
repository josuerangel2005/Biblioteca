package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.domain.repository.CategoryRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public List<Category> getAllCategory() {
    return this.categoryRepository.getAllCategory();
  }

  @Transactional(readOnly = true)
  public Category getCategoryById(int id) {
    return this.categoryRepository.getCategoryById(id);
  }

  @Transactional
  public Category saveCategory(CategorySave category) {
    return this.categoryRepository.saveCategory(category);
  }

  @Transactional
  public Category updateCategory(CategorySave category, int id) {
    return this.categoryRepository.updateCategory(category, id);
  }

  @Transactional
  public void deleteCategory(int id) {
    this.categoryRepository.deleteCategoryById(id);
  }
}
