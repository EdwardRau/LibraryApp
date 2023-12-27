package com.libraryapp.libraryapp.common;

import java.time.LocalDate;

public class BookDto {
        Long id;
        String title;
        String author;
        String genre;
        String ownerName;
        boolean isLoaned;
        LocalDate endDate;


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

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BookDto(Long id, String title, String author, String genre, String ownerName, LocalDate endDate, boolean isLoaned) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ownerName = ownerName;
        this.endDate=endDate;
        this.isLoaned=isLoaned;
    }
}
