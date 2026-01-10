package com.biblioteca.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.Category.CategorySave;
import com.biblioteca.persistence.entity.Categoria;

@Mapper(componentModel = "spring")
public interface CategorySaveMapper {

  @Mapping(source = "nombre", target = "name")
  CategorySave toCategorySave(Categoria categoria);

  @InheritInverseConfiguration
  @Mapping(target = "idCategoria", ignore = true)
  @Mapping(target = "libroCategorias", ignore = true)
  Categoria toCategoria(CategorySave categorySave);
}
