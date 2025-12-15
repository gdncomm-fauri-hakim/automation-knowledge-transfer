package com.gdn.qa.module.api.data;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data holder for BookStore operations in DemoQA API
 * This component stores book-related data across test steps
 * 
 * @author QA Automation Team
 */
@Data
@Component("com.gdn.qa.module.api.data.BookStoreData")
@Scope("cucumber-glue")
public class BookStoreData {
    
    // Book identifiers
    private String isbn;
    private List<String> isbnList = new ArrayList<>();
    
    // Book details (for single book)
    private String title;
    private String subtitle;
    private String author;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;
    
    // Collection data (for multiple books)
    private List<Map<String, Object>> booksCollection = new ArrayList<>();
    
    // Response data storage
    private Map<String, Object> getAllBooksResponse;
    private Map<String, Object> getBookResponse;
    private Map<String, Object> addBooksResponse;
    private Map<String, Object> updateBookResponse;
    private Map<String, Object> deleteBookResponse;
    private Map<String, Object> deleteBooksResponse;
    
    // Custom learning: Storage for extracted book data
    private List<Map<String, Object>> extractedBooks = new ArrayList<>();
    private Integer totalBooks;
    private List<String> bookTitles = new ArrayList<>();
    private List<String> bookAuthors = new ArrayList<>();
    
    /**
     * Reset all bookstore data - useful for cleanup between scenarios
     */
    public void resetBookStoreData() {
        this.isbn = null;
        this.isbnList.clear();
        this.title = null;
        this.subtitle = null;
        this.author = null;
        this.publisher = null;
        this.pages = null;
        this.description = null;
        this.website = null;
        this.booksCollection.clear();
        this.getAllBooksResponse = null;
        this.getBookResponse = null;
        this.addBooksResponse = null;
        this.updateBookResponse = null;
        this.deleteBookResponse = null;
        this.deleteBooksResponse = null;
        this.extractedBooks.clear();
        this.totalBooks = null;
        this.bookTitles.clear();
        this.bookAuthors.clear();
    }
    
    /**
     * Add ISBN to the collection list
     * @param isbn ISBN to add
     */
    public void addIsbn(String isbn) {
        if (!this.isbnList.contains(isbn)) {
            this.isbnList.add(isbn);
        }
    }
    
    /**
     * Remove ISBN from the collection list
     * @param isbn ISBN to remove
     */
    public void removeIsbn(String isbn) {
        this.isbnList.remove(isbn);
    }
}
