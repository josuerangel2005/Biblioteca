package com.biblioteca.persistence.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.domain.dto.Loan.Loan;
import com.biblioteca.domain.dto.Loan.LoanBook;
import com.biblioteca.domain.dto.Loan.LoanSave;
import com.biblioteca.domain.dto.Loan.LoanUpdate;
import com.biblioteca.domain.exception.BookNotExistsException;
import com.biblioteca.domain.exception.LoanAlreadyExistsException;
import com.biblioteca.domain.exception.LoanNotExistsException;
import com.biblioteca.domain.exception.UserNotExistsException;
import com.biblioteca.domain.repository.LoanRepository;
import com.biblioteca.persistence.crud.AutorCrudRepository;
import com.biblioteca.persistence.crud.LibroCrudRepository;
import com.biblioteca.persistence.crud.PrestamoCrudRepository;
import com.biblioteca.persistence.crud.UsuarioCrudRepository;
import com.biblioteca.persistence.entity.Prestamo;
import com.biblioteca.persistence.mapper.LoanMapper;
import com.biblioteca.persistence.mapper.LoanSaveMapper;
import com.biblioteca.persistence.mapper.LoanUpdateMapper;

@Repository
public class PrestamoRepository implements LoanRepository {

  @Autowired
  private PrestamoCrudRepository prestamoCrudRepository;

  @Autowired
  private LoanMapper loanMapper;

  @Autowired
  private LoanSaveMapper loanSaveMapper;

  @Autowired
  private LoanUpdateMapper loanUpdateMapper;

  @Autowired
  private LibroCrudRepository libroCrudRepository;

  @Autowired
  private UsuarioCrudRepository usuarioCrudRepository;

  @Override
  public void delete(int id) {
    this.prestamoCrudRepository.findById(id).orElseThrow(() -> new LoanNotExistsException(id));
  }

  @Override
  public Loan findById(int id) {
    return this.loanMapper
        .toLoan(this.prestamoCrudRepository.findById(id).orElseThrow(() -> new LoanNotExistsException(id)));
  }

  @Override
  public List<Loan> getAll() {
    return this.loanMapper.toLoans(this.prestamoCrudRepository.findAll());
  }

  @Override
  public Loan save(LoanSave loanSave) {
    if (this.prestamoCrudRepository.findFirstByIdUsuarioAndFechaPrestamo(loanSave.userId(),
        loanSave.loanDate()) != null)
      throw new LoanAlreadyExistsException(loanSave.userId(), this.fechaToString(loanSave.loanDate()));

    this.validarIdUsuario(loanSave.userId());

    loanSave.books().forEach(book -> this.validarIdLibro(book.bookId()));

    Prestamo toSave = this.loanSaveMapper.toPrestamo(loanSave);

    toSave.getPrestamoLibros().forEach(prestamoLibro -> prestamoLibro.setPrestamo(toSave));

    return this.loanMapper.toLoan(this.prestamoCrudRepository.save(toSave));
  }

  @Override
  public Loan update(LoanUpdate loanUpdate, int id) {
    loanUpdate.books().forEach(book -> this.validarIdLibro(book.bookId()));
    this.validarIdLibro(loanUpdate.userId());

    Prestamo toUpdate = this.prestamoCrudRepository.findById(id).orElseThrow(() -> new LoanNotExistsException(id));

    this.loanUpdateMapper.updatePrestamoFromLoan(loanUpdate, toUpdate);

    return this.loanMapper.toLoan(this.prestamoCrudRepository.save(toUpdate));

  }

  public String fechaToString(LocalDateTime fecha) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return dateTimeFormatter.format(fecha);
  }

  public void validarIdLibro(int id) {
    this.libroCrudRepository.findById(id).orElseThrow(() -> new BookNotExistsException(id));
  }

  public void validarIdUsuario(int id) {
    this.usuarioCrudRepository.findById(id).orElseThrow(() -> new UserNotExistsException(id));
  }
}
