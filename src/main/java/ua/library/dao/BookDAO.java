package ua.library.dao;

import org.hibernate.Criteria;
import ua.library.model.Pagination;
import ua.library.model.entities.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book, Long> {
    boolean containBook(Book book);

    void createCriteria(Integer query, Object criteria);
    Long bookCount();
    List<Book> getRowBook(int from, int to);

    Object getFieldValue(Long id, String value);
}
