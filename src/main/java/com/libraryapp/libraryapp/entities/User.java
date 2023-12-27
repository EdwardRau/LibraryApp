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
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue
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
    private Collection<Book> oneToMany;
    @OneToMany(mappedBy = "owner")
    public Collection<Book> getOneToMany() {
        return oneToMany;
    }
    public void setOneToMany(Collection<Book> oneToMany) {
        this.oneToMany = oneToMany;
    }
}