package com.biblioteca.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biblioteca.domain.dto.BookCategory;
import com.biblioteca.domain.dto.Book.CategoryId;
import com.biblioteca.persistence.entity.LibroCategoria;

@Mapper(componentModel = "spring")
public interface BookCategoryMapper {
  @Mapping(source = "categoria.nombre", target = "name")
  BookCategory toBookCategory(LibroCategoria libroCategoria);

  @Mapping(source = "libroCategoriaPK.idCategoria", target = "categoryId")
  CategoryId toCategoryId(LibroCategoria libroCategoria);

  List<CategoryId> tCategoryIds(List<LibroCategoria> libroCategorias);

}
