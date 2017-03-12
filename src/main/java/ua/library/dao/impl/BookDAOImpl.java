package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.BookDAO;
import ua.library.model.BookForm;
import ua.library.model.Pages;
import ua.library.model.entities.Book;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO{

    private final static String DELETE_QUERY = "delete from Book where id = :id";

    @Autowired
    private SessionFactory sessionFactory;

    private ProjectionList projectionList;

    public BookDAOImpl(){
        projectionList = Projections.projectionList();
        projectionList.add(Projections.property("id"), "id");
        projectionList.add(Projections.property("name"), "name");
        projectionList.add(Projections.property("pageCount"), "pageCount");
        projectionList.add(Projections.property("isbn"), "isbn");
        projectionList.add(Projections.property("author"), "author");
        projectionList.add(Projections.property("publishYear"), "publishYear");
        projectionList.add(Projections.property("publisher"), "publisher");
        projectionList.add(Projections.property("genre"), "genre");
    }

    @Transactional
    public boolean containBook(Book book) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.ilike("name", book.getName()));
        if(criteria.uniqueResult() != null){
            return true;
        }
        return false;
    }

    @Transactional
    public Book findBookById(Long id){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("id", id));
        return (Book) criteria.uniqueResult();
    }

    @Transactional
    public void getAllBooks(Pages pages){
        executeCriterion(pages);
    }

    @Transactional
    public void getBooksByGenre(Long id, Pages pages){
        Criterion criterion = Restrictions.eq("genre.id", id);
        executeCriteria(criterion, pages);
    }

    @Transactional
    public void getBooksByLetter(Character letter, Pages pages){
        Criterion criterion = Restrictions.ilike("b.name", letter.toString(), MatchMode.START);
        executeCriteria(criterion, pages);
    }

    @Transactional
    public void getBooksByAuthor(String author, Pages pages){
        Criterion criterion = Restrictions.ilike("author.fio", author, MatchMode.ANYWHERE);

        executeCriteria(criterion, pages);
    }

    @Transactional
    public void getBooksByText(String text, Pages pages){
        Criterion criterion = Restrictions.ilike("b.name", text, MatchMode.ANYWHERE);

        executeCriteria(criterion, pages);
    }

    @Transactional
    public void deleteBook(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery(DELETE_QUERY).setLong("id",id).executeUpdate();
    }

    @Transactional
    public void addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }

    @Transactional
    public Object getFieldValue(Long id, String value){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.setProjection(Projections.property(value));
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();
    }

    private Criteria createCriteria(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class, "b");
        createAliased(criteria);
        return criteria;
    }

    private Criteria createCriteria(Criterion criterion){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class, "b");
        createAliased(criteria);
        criteria.add(criterion);
        return criteria;
    }

    private void createAliased(Criteria criteria){
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }

    private List<Book> bookListCriteria(Criteria booksCriteria, int from, int to) {

        booksCriteria.addOrder(Order.asc("b.name")).setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(Book.class));

        booksCriteria.setFirstResult(from).setMaxResults(to);

        return booksCriteria.list();
    }

    private Long countCriteria(Criteria booksCriteria) {
        return (Long) booksCriteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void executeCriterion(Pages pages){
        Criteria criteria = createCriteria();
        Long count = countCriteria(criteria);
        pages.setBooksCount(count);

        List<Book> books = bookListCriteria(criteria, pages.getFrom(), pages.getTo());
        pages.setBooks(books);
    }

    private void executeCriteria(Criterion criterion, Pages pages){
        Criteria criteria = createCriteria(criterion);
        Long count = countCriteria(criteria);
        pages.setBooksCount(count);

        List<Book> books = bookListCriteria(criteria, pages.getFrom(), pages.getTo());
        pages.setBooks(books);
    }
}
