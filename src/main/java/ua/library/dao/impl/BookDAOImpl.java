package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import ua.library.model.Pages;
import ua.library.model.entities.Book;
import ua.library.dao.BookDAO;

import java.util.List;

@Repository(value = "BookDAO")
public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {

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

    @Override
    public boolean containBook(Book book) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.add(Restrictions.ilike("name", book.getName()));
        if(criteria.uniqueResult() != null){
            return true;
        }
        return false;
    }

    @Override
    public void getBooks(Pages pages){
        executeCriteria(null, pages);
    }

    @Override
    public void getBooksByGenre(Long id, Pages pages) {
        Criterion criterion = Restrictions.eq("genre.id", id);
        executeCriteria(criterion, pages);
    }

    @Override
    public void getBooksByLetter(Character letter, Pages pages) {
        Criterion criterion = Restrictions.ilike("b.name", letter.toString(), MatchMode.START);
        executeCriteria(criterion, pages);
    }

    @Override
    public void getBooksByAuthor(String author, Pages pages) {
        Criterion criterion = Restrictions.ilike("author.fio", author, MatchMode.ANYWHERE);
        executeCriteria(criterion, pages);
    }

    @Override
    public void getBooksByText(String text, Pages pages) {
        Criterion criterion = Restrictions.ilike("b.name", text, MatchMode.ANYWHERE);

        executeCriteria(criterion, pages);
    }

    @Override
    public Object getFieldValue(Long id, String value) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.setProjection(Projections.property(value));
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();
    }

    private void createAliased(Criteria criteria){
        criteria.createAlias("b.author", "author");
        criteria.createAlias("b.genre", "genre");
        criteria.createAlias("b.publisher", "publisher");
    }

    private Long bookCountCriteria(Criteria booksCriteria) {
        return (Long) booksCriteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private List<Book> bookListCriteria(Criteria booksCriteria, int from, int to) {

        booksCriteria.addOrder(Order.asc("b.name")).setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(Book.class));

        booksCriteria.setFirstResult(from).setMaxResults(to);

        return booksCriteria.list();
    }

    private Criteria createCriteria(Criterion criterion){
        Criteria criteria = getSession().createCriteria(Book.class, "b");
        createAliased(criteria);
        if(criterion != null) criteria.add(criterion);
        return criteria;
    }

    private void executeCriteria(Criterion criterion, Pages pages){
        Criteria criteria = createCriteria(criterion);
        Long count = bookCountCriteria(criteria);
        pages.setBooksCount(count);

        List<Book> books = bookListCriteria(criteria, pages.getFrom(), pages.getTo());
        pages.setBooks(books);
    }
}
