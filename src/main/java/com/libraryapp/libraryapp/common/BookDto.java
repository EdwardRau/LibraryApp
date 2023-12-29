package com.libraryapp.libraryapp.common;

import java.time.LocalDate;

public class BookDto {
    Long id;
    String title;
    String author;
    String genre;
    boolean isLoaned;
    String imagePath;

    public String getImagePath() {
        return imagePath;
    }

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
    String description;

    public String getDescription() {
        return description;
    }

    public BookDto(Long id, String title, String author, String genre, boolean isLoaned, String description,String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description=description;
        this.isLoaned = isLoaned;
        this.imagePath=imagePath;
    }
}
