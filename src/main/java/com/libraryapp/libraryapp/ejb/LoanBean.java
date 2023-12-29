package com.libraryapp.libraryapp.ejb;

import com.libraryapp.libraryapp.common.LoanDto;
import com.libraryapp.libraryapp.entities.Book;
import com.libraryapp.libraryapp.entities.Loan;
import com.libraryapp.libraryapp.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class LoanBean {
    private static final Logger LOG = Logger.getLogger(LoanBean.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    public void createLoan(Long bookId, LocalDate startDate, LocalDate endDate) {
        Book book = entityManager.find(Book.class, bookId);
        if (book != null && !book.isLoaned()) {
            Loan loan = new Loan();
            loan.setStartDate(startDate);
            loan.setEndDate(endDate);
            loan.setBook(book);
            Collection<Loan> bookLoans = book.getLoans();
            if (bookLoans == null) {
                bookLoans = new ArrayList<>();
                book.setLoans(bookLoans);
            }
            bookLoans.add(loan);
            entityManager.persist(loan);
            book.setLoaned(true);
        }
    }
    public User getBookOwner(Long bookId) {
        LOG.info("getBookOwner");
        Book book = entityManager.find(Book.class, bookId);

        if (book != null) {
            // Retrieve the latest loan associated with the book
            Loan latestLoan = getLatestLoan(book);

            // If there's a loan, return the user associated with the loan
            return (latestLoan != null) ? latestLoan.getUser() : null;
        }
        return null;
    }
    private Loan getLatestLoan(Book book) {
        TypedQuery<Loan> query = entityManager.createQuery(
                        "SELECT l FROM Loan l WHERE l.book = :book ORDER BY l.startDate DESC", Loan.class)
                .setParameter("book", book)
                .setMaxResults(1);

        List<Loan> loans = query.getResultList();
        return loans.isEmpty() ? null : loans.get(0);
    }
}
