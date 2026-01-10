package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.biblioteca.domain.dto.Category.Category;
import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  @Mapping(source = "idCategoria", target = "CategoryId")
  @Mapping(source = "nombre", target = "name")
  Category toCategory(Categoria categoria);

  List<Category> toCategorys(List<Categoria> categorias);

  @InheritInverseConfiguration
  @Mapping(target = "libroCategorias", ignore = true)
  Categoria toCategoria(Category category);

  @Mapping(target = "nombre", source = "name")
  @Mapping(target = "libroCategorias", ignore = true)
  void UpdateCategoriaFromCategory(CategorySave categorySave, @MappingTarget Categoria categoria);

}
