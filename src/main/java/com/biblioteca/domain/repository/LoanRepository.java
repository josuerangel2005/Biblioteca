package com.biblioteca.domain.repository;

import java.util.List;

import com.biblioteca.domain.dto.Loan.Loan;
import com.biblioteca.domain.dto.Loan.LoanSave;
import com.biblioteca.domain.dto.Loan.LoanUpdate;

public interface LoanRepository {
  List<Loan> getAll();

  Loan findById(int id);

  Loan save(LoanSave loanSave);

  Loan update(LoanUpdate loanUpdate, int id);

  void delete(int id);
}
