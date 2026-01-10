package com.biblioteca.domain.repository;

import java.util.List;

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;

public interface CategoryRepository {
  List<Category> getAllCategory();

  Category getCategoryById(int id);

  Category saveCategory(CategorySave categorySave);

  Category updateCategory(CategorySave categorySave, int id);

  void deleteCategoryById(int id);
}
