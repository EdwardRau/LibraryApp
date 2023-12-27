package com.libraryapp.libraryapp.ejb;

import com.libraryapp.libraryapp.common.LoanDto;
import com.libraryapp.libraryapp.entities.Book;
import com.libraryapp.libraryapp.entities.Loan;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;

@Stateless
public class LoanBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void createLoan(Long bookId, LocalDate startDate, LocalDate endDate) {
        Book book = entityManager.find(Book.class, bookId);

        // Check if the book exists and is not already loaned
        if (book != null && !book.isLoaned()) {
            Loan loan = new Loan();
            loan.setStartDate(startDate);
            loan.setEndDate(endDate);
            loan.setBooks(book);
            entityManager.persist(loan);
            book.setLoan(loan);
            book.setLoaned(true);
        }
    }
}
