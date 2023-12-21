package com.libraryapp.libraryapp.common;

public class UserDto {
    Long id;
    String username;
    String email;
    public UserDto(Long id, String username, String role,String email) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
    String role;
}
