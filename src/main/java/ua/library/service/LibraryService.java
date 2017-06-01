package ua.library.service;

import ua.library.model.Pagination;
import ua.library.model.entities.Book;
import ua.library.model.entities.Genre;

import java.util.List;

public interface LibraryService {
    int GENRE_CRITERIA = 1;
    int LETTER_CRITERIA = 2;
    int AUTHOR_CRITERIA = 3;
    int TEXT_CRITERIA = 4;


    List<Genre> getAllGenre();
    byte[] getImage(Long id);
    byte[] getBookContent(Long id);
    Book findBookById(Long id);
    Pagination getBooksOnPage(int query, int page, Object criteria);
}
