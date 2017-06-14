package ua.library.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.library.model.entities.Book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookForm {
    private Long id;

    @NotNull
    @Size(min = 1, max = 30, message = "Введите название книги")
    private String name;

    @Min(value = 1, message = "Введите количество страниц")
    private int pageCount;

    @NotNull
    @Size(min = 1, max = 20, message = "Введите ISBN")
    private String isbn;

    @NotNull
    @Size(min = 1, max = 20, message = "Введите жанр")
    private String genre;

    @NotNull
    @Size(min = 1, max = 50, message = "Введите имя автора")
    private String author;

    @Min(value = 1000, message = "Введите год издания")
    private int publishYear;

    @NotNull
    @Size(min = 1, max = 30, message = "Введите кто опубликовал книгу")
    private String publisher;

    @NotNull
    private CommonsMultipartFile image;

    @NotNull
    private CommonsMultipartFile content;

    public BookForm(){

    }

    public BookForm(Book book){
        this.id = book.getId();
        this.name = book.getName();
        this.pageCount = book.getPageCount();
        this.isbn = book.getIsbn();
        this.genre = book.getGenre().getName();
        this.author = book.getAuthor().getFio();
        this.publishYear = book.getPublishYear();
        this.publisher = book.getPublisher().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public CommonsMultipartFile getImage() {
        return image;
    }

    public void setImage(CommonsMultipartFile image) {
        this.image = image;
    }

    public CommonsMultipartFile getContent() {
        return content;
    }

    public void setContent(CommonsMultipartFile content) {
        this.content = content;
    }
}
