package com.libraryapp.libraryapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;
    private Book book;
    @OneToOne(mappedBy = "loan")
    public Book getBook(){return book;}

    public void setBooks(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}