package ua.library.dao;

import ua.library.model.Pages;
import ua.library.model.entities.Book;

public interface BookDAO extends GenericDAO<Book, Long> {
    boolean containBook(Book book);
    void getBooks(Pages pages);
    void getBooksByGenre(Long id, Pages pages);
    void getBooksByLetter(Character letter, Pages pages);
    void getBooksByAuthor(String author, Pages pages);
    void getBooksByText(String text, Pages pages);

    Object getFieldValue(Long id, String value);
}
