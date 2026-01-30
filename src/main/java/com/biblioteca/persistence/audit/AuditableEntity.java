package com.biblioteca.persistence.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditableEntity {
  @Column(name = "create_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "modified_date")
  @LastModifiedDate
  private LocalDateTime modifiedDate;

  @Column(name = "created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "modified_by")
  @LastModifiedBy
  private String modifiedBy;
}
