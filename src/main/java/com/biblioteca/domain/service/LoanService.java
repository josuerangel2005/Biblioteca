package com.biblioteca.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.domain.dto.Loan.Loan;
import com.biblioteca.domain.dto.Loan.LoanSave;
import com.biblioteca.domain.dto.Loan.LoanUpdate;
import com.biblioteca.persistence.projection.Reporte;
import com.biblioteca.persistence.repository.PrestamoRepository;

@Service
public class LoanService {
  @Autowired
  private PrestamoRepository prestamoRepository;

  @Transactional(readOnly = true)
  public List<Loan> getAll() {
    return this.prestamoRepository.getAll();
  }

  @Transactional(readOnly = true)
  public Loan findById(int id) {
    return this.prestamoRepository.findById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  public Loan save(LoanSave loanSave) {
    return this.prestamoRepository.save(loanSave);
  }

  @Transactional(rollbackFor = Exception.class)
  public Loan update(LoanUpdate loanUpdate, int id) {
    return this.prestamoRepository.update(loanUpdate, id);
  }

  @Transactional(rollbackFor = Exception.class)
  public void delete(int id) {
    this.prestamoRepository.delete(id);
  }

  @Transactional(readOnly = true)
  public List<Loan> getAllNotDelivered() {
    return this.prestamoRepository.getAllNotDelivered();
  }

  @Transactional(readOnly = true)
  public Loan getPrestamoByIdWithBooks(int idLoan) {
    return this.prestamoRepository.getPrestamoByIdWithBooks(idLoan);
  }

  @Transactional(readOnly = true)
  public List<Reporte> getReportes() {
    return this.prestamoRepository.getReportes();
  }
}
