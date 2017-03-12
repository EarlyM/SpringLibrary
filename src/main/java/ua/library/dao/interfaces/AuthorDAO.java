package ua.library.dao.interfaces;

import ua.library.model.entities.Author;

public interface AuthorDAO {
    Author findAuthor(String author);
    void addAuthor(Author author);
}
