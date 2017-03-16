package ua.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.AuthorDAO;
import ua.library.dao.interfaces.BookDAO;
import ua.library.dao.interfaces.GenreDAO;
import ua.library.dao.interfaces.PublisherDAO;
import ua.library.model.BookForm;
import ua.library.model.entities.Author;
import ua.library.model.entities.Book;
import ua.library.model.entities.Genre;
import ua.library.model.entities.Publisher;
import ua.library.service.AdministrationService;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    private static final String IMG = "image";
    private static final String CONTENT = "content";

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private GenreDAO genreDAO;
    @Autowired
    private PublisherDAO publisherDAO;
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public void addBook(BookForm bookForm) {
//        Author author = findAuthor(bookForm.getAuthor());
//        Genre genre = findGenre(bookForm.getGenre());
//        Publisher publisher = findPublisher(bookForm.getPublisher());

        Author author = new Author(bookForm.getAuthor());
        Genre genre = new Genre(bookForm.getGenre());
        Publisher publisher = new Publisher(bookForm.getPublisher());

        Book book = new Book();
        book.setName(bookForm.getName());
        book.setContent(bookForm.getContent().getBytes());
        book.setPageCount(bookForm.getPageCount());
        book.setIsbn(bookForm.getIsbn());
        book.setGenre(genre);
        book.setAuthor(author);
        book.setPublishYear(bookForm.getPublishYear());
        book.setPublisher(publisher);
        book.setImage(bookForm.getImage().getBytes());

        if(!bookDAO.containBook(book)) {
            bookDAO.addBook(book);
        }

    }

    @Override
    public void editBook(BookForm bookForm) {
        Author author = findAuthor(bookForm.getAuthor());
        Genre genre = findGenre(bookForm.getGenre());
        Publisher publisher = findPublisher(bookForm.getPublisher());

        Book book = new Book();
        book.setId(bookForm.getId());
        book.setName(bookForm.getName());
        if(bookForm.getContent() != null) {
            book.setContent(bookForm.getContent().getBytes());
        } else {
            book.setContent((byte[]) bookDAO.getFieldValue(book.getId(), CONTENT));
        }
        book.setPageCount(bookForm.getPageCount());
        book.setIsbn(bookForm.getIsbn());
        book.setGenre(genre);
        book.setAuthor(author);
        book.setPublishYear(bookForm.getPublishYear());
        book.setPublisher(publisher);
        if(bookForm.getImage() != null) {
            book.setImage(bookForm.getImage().getBytes());
        } else {
            book.setImage((byte[]) bookDAO.getFieldValue(book.getId(), CONTENT));
        }

        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookDAO.deleteBook(id);
    }

    private Author findAuthor(String fio){
        Author author = authorDAO.findAuthor(fio);
        if(author == null){
            Author newAuthor = new Author();
            newAuthor.setFio(fio);
            authorDAO.addAuthor(newAuthor);
            author = authorDAO.findAuthor(fio);
        }
        return author;
    }

    private Genre findGenre(String name){
        Genre genre = genreDAO.getGenreByName(name);
        if(genre == null){
            Genre newGenre = new Genre();
            newGenre.setName(name);
            genreDAO.save(newGenre);
            genre = genreDAO.getGenreByName(name);
        }
        return genre;
    }

    private Publisher findPublisher(String name){
        Publisher publisher = publisherDAO.findPublisher(name);
        if(publisher == null){
            Publisher newPublisher = new Publisher();
            newPublisher.setName(name);
            publisherDAO.save(newPublisher);
            publisher = publisherDAO.findPublisher(name);
        }
        return publisher;
    }
}
