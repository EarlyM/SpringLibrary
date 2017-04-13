package ua.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.BookDAO;
import ua.library.dao.interfaces.GenreDAO;
import ua.library.model.Pages;
import ua.library.model.entities.Book;
import ua.library.model.entities.Genre;
import ua.library.service.LibraryService;

import java.util.List;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private static final String IMG = "image";
    private static final String CONTENT = "content";

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private GenreDAO genreDAO;

    @Override
    public void getAllBook(Pages pages) {
        bookDAO.getAllBooks(pages);
    }

    @Override
    public void getBookByText(String text, String searchCriteria, Pages pages) {
        switch (searchCriteria){
            case "book":
                bookDAO.getBooksByText(text, pages);
                break;
            case "author":
                bookDAO.getBooksByAuthor(text, pages);
                break;
        }
    }

    @Override
    public void getBookByGenre(Long id, Pages pages) {
        bookDAO.getBooksByGenre(id, pages);
    }

    @Override
    public void getBookByLetter(Character letter, Pages pages) {
        bookDAO.getBooksByLetter(letter, pages);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreDAO.getGenres();
    }

    @Override
    public byte[] getImage(Long id) {
        return (byte[]) bookDAO.getFieldValue(id, IMG);
    }

    @Override
    public byte[] getBookContent(Long id) {
        return (byte[]) bookDAO.getFieldValue(id, CONTENT);
    }

    @Override
    public Book findBookById(Long id) {
        return bookDAO.findBookById(id);
    }
}
