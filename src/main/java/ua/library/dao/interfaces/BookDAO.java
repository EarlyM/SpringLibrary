package ua.library.dao.interfaces;

import ua.library.model.Pages;
import ua.library.model.entities.Book;

import java.util.List;

public interface BookDAO {

    boolean containBook(Book book);
    Book findBookById(Long id);
    void getAllBooks(Pages pages);
    void getBooksByGenre(Long id,Pages pages);
    void getBooksByLetter(Character letter, Pages pages);
    void getBooksByAuthor(String author, Pages pages);
    void getBooksByText(String text, Pages pages);
    void deleteBook(Long id);
    void addBook(Book book);
    void updateBook(Book book);

    Object getFieldValue(Long id, String value);
}
