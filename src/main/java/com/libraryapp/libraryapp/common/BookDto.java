package com.libraryapp.libraryapp.common;

import java.time.LocalDate;

public class BookDto {
    Long id;
    String title;
    String author;
    String genre;
    boolean isLoaned;

    public boolean isLoaned() {
        return isLoaned;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }


    public BookDto(Long id, String title, String author, String genre,  boolean isLoaned) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;

        this.isLoaned = isLoaned;

    }
}
