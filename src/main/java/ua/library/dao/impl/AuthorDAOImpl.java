package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.AuthorDAO;
import ua.library.model.entities.Author;

import java.io.Serializable;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Author findAuthor(String author) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Author.class);
        criteria.add(Restrictions.ilike("fio", author));
        return (Author) criteria.uniqueResult();
    }

    public void addAuthor(Author author){
        sessionFactory.getCurrentSession().save(author);
    }
}
