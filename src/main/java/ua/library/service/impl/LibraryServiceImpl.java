package ua.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.model.Pagination;
import ua.library.model.entities.Book;
import ua.library.model.entities.Genre;
import ua.library.dao.BookDAO;
import ua.library.dao.GenreDAO;
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
    public List<Genre> getAllGenre() {
        return genreDAO.findAll();
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
        return bookDAO.find(id);
    }

    @Override
    public Pagination getBooksOnPage(int query, int page, Object criteria){
        Pagination pagination = new Pagination();
        pagination.setSelectPage(page);
        bookDAO.createCriteria(query, criteria);
        pagination.setPageCount(bookDAO.bookCount());
        pagination.setBooks(bookDAO.getRowBook(pagination.getFrom(), pagination.getTo()));
        return pagination;
    }
}
