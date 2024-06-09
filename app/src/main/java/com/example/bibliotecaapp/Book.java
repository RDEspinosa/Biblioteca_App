package com.example.bibliotecaapp;

public class Book {

    private String title;
    private String author;
    private String year;
    private String isbn;
    private String description;
    private boolean available;

    public Book(String title, String author, String year, String isbn, String description, boolean available) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.description = description;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
