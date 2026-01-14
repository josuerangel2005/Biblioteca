package com.biblioteca.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.domain.dto.Loan.Loan;
import com.biblioteca.domain.dto.Loan.LoanSave;
import com.biblioteca.domain.dto.Loan.LoanUpdate;
import com.biblioteca.domain.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
  @Autowired
  private LoanService loanService;

  @GetMapping
  public ResponseEntity<List<Loan>> getAll() {
    return new ResponseEntity<List<Loan>>(this.loanService.getAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Loan> findById(@PathVariable int id) {
    return new ResponseEntity<Loan>(this.loanService.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Loan> save(@RequestBody LoanSave loanSave) {
    return new ResponseEntity<Loan>(this.loanService.save(loanSave), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Loan> update(@RequestBody LoanUpdate loanUpdate, @PathVariable int id) {
    return new ResponseEntity<Loan>(this.loanService.update(loanUpdate, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable int id) {
    this.loanService.delete(id);
    return ResponseEntity.ok().build();
  }
}
