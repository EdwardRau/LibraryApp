package com.libraryapp.libraryapp.entities;
import jakarta.persistence.*;
import java.util.Collection;
@Entity
public class User {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    private String username;

    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;
    private String role;
    @OneToMany(mappedBy = "user")
    private Collection<Loan> loans;
    public Collection<Loan> getLoans() {
        return loans;
    }
    public void setLoans(Collection<Loan> loans) {
        this.loans = loans;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Collection<Book> books;

    @OneToMany(mappedBy = "owner")
    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}