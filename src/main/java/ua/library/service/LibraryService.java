package ua.library.service;

import ua.library.model.Pages;
import ua.library.model.entities.Book;
import ua.library.model.entities.Genre;

import java.util.List;

public interface LibraryService {
    void getAllBook(Pages pages);
    void getBookByText(String text, String searchCriteria, Pages pages);
    void getBookByGenre(Long id, Pages pages);
    void getBookByLetter(Character letter, Pages pages);
    List<Genre> getAllGenre();
    byte[] getImage(Long id);
    byte[] getBookContent(Long id);
    Book findBookById(Long id);
}
