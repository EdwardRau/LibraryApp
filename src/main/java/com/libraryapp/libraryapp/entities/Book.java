package com.libraryapp.libraryapp.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Book {
    private Long id;
    private boolean isLoaned;
    public boolean isLoaned() {
        return isLoaned;
    }
    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    private String title;
    @Basic
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    private String author;
    private String genre;
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    private Collection<Loan> loans;
    @OneToMany(mappedBy = "book")
    public Collection<Loan> getLoans() {
        return loans;
    }
    public void setLoans(Collection<Loan> loans) {
        this.loans = loans;
    }
}