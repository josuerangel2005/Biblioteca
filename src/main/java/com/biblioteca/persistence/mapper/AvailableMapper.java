package com.biblioteca.persistence.mapper;

import org.mapstruct.Named;

public class AvailableMapper {
  @Named("BooleanToString")
  public static String toString(Boolean disponible) {
    if (disponible == null)
      return null;
    return disponible ? "Si" : "No";
  }

  @Named("StringToBoolean")
  public static Boolean toBoolean(String available) {
    if (available == null)
      return null;
    return available.equalsIgnoreCase("si");
  }
}
