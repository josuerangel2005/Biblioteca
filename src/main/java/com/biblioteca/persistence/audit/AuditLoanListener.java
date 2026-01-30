package com.biblioteca.persistence.audit;

import org.springframework.util.SerializationUtils;

import com.biblioteca.persistence.entity.Prestamo;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditLoanListener {
  private Prestamo currentValue;

  @PostLoad
  public void postLoad(Prestamo prestamo) {
    System.out.println("POST LOAD");
    this.currentValue = SerializationUtils.clone(currentValue);
  }

  @PostPersist
  @PostUpdate
  public void onPostPersist(Prestamo prestamo) {
    System.out.println("POST PERSIST OR UPDATE");
    System.out.println("OLD VALUE: " + this.currentValue.toString());
    System.out.println("NEW VALUE: " + prestamo.toString());
  }

  @PreRemove
  public void onPredelete(Prestamo prestamo) {
    System.out.println(prestamo.toString());
  }
}
