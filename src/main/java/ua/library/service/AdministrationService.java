package ua.library.service;

import ua.library.model.BookForm;

public interface AdministrationService {
    void addBook(BookForm bookForm);
    void editBook(BookForm bookForm);
    void deleteBook(Long id);
}
