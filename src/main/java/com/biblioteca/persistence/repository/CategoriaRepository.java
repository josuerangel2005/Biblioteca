package com.biblioteca.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.domain.exception.CategoryAlreadyExistsException;
import com.biblioteca.domain.exception.CategoryNotExistsException;
import com.biblioteca.domain.repository.CategoryRepository;
import com.biblioteca.persistence.crud.CategoriaCrudRepository;
import com.biblioteca.persistence.entity.Categoria;
import com.biblioteca.persistence.mapper.CategoryMapper;
import com.biblioteca.persistence.mapper.CategorySaveMapper;

@Repository
public class CategoriaRepository implements CategoryRepository {
  @Autowired
  private CategoriaCrudRepository categoriaCrudRepository;

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private CategorySaveMapper categorySaveMapper;

  @Override
  public List<Category> getAllCategory() {
    return this.categoryMapper.toCategorys(this.categoriaCrudRepository.findAll());
  }

  @Override
  public Category saveCategory(CategorySave categorySave) {
    if (this.categoriaCrudRepository.findFirstByNombre(categorySave.name()) != null)
      throw new CategoryAlreadyExistsException(categorySave.name());
    return this.categoryMapper
        .toCategory(this.categoriaCrudRepository.save(this.categorySaveMapper.toCategoria(categorySave)));
  }

  @Override
  public Category updateCategory(CategorySave categorySave, int id) {
    Categoria toUpdate = this.categoriaCrudRepository.findById(id)
        .orElseThrow(() -> new CategoryNotExistsException(id));

    this.categoryMapper.UpdateCategoriaFromCategory(categorySave, toUpdate);

    return this.categoryMapper.toCategory(this.categoriaCrudRepository.save(toUpdate));
  }

  @Override
  public Category getCategoryById(int id) {
    return this.categoryMapper.toCategory(this.categoriaCrudRepository.findById(id)
        .orElseThrow(() -> new CategoryNotExistsException(id)));
  }

  @Override
  public void deleteCategoryById(int id) {
    this.categoriaCrudRepository.findById(id)
        .orElseThrow(() -> new CategoryNotExistsException(id));
  }

}
