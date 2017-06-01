package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import ua.library.model.entities.Book;
import ua.library.dao.BookDAO;

import java.util.List;

@Repository
public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {

    private ProjectionList projectionList;
    private Criteria criteria;

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

    private Criteria prepareCriteria(Criterion criterion){
        Criteria criteria = getSession().createCriteria(Book.class, "b");
        createAliased(criteria);
        if(criterion != null) criteria.add(criterion);
        return criteria;
    }

    @Override
    public void createCriteria(Integer query, Object criteria){
        Criterion temp = null;
        switch (query){
            case 1:
                temp = Restrictions.eq("genre.id", criteria);
                break;
            case 2:
                temp = Restrictions.ilike("b.name", criteria.toString(), MatchMode.START);
                break;
            case 3:
                temp = Restrictions.ilike("author.fio", criteria.toString(), MatchMode.ANYWHERE);
                break;
            case 4:
                temp = Restrictions.ilike("b.name", criteria.toString(), MatchMode.ANYWHERE);
                break;
        }
        this.criteria = prepareCriteria(temp);
    }

    @Override
    public Long bookCount() {
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Book> getRowBook(int from, int to){
        criteria.addOrder(Order.asc("b.name")).setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(Book.class));

        criteria.setFirstResult(from).setMaxResults(to);

        return criteria.list();
    }
}
