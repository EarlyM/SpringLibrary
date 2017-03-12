package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.GenreDAO;
import ua.library.model.entities.Genre;

import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Genre> getGenres() {
        List<Genre> genres = sessionFactory.getCurrentSession().createCriteria(Genre.class).list();
        return genres;
    }

    @Transactional
    public Genre getGenreByName(String genre) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Genre.class);
        criteria.add(Restrictions.ilike("name", genre));
        return (Genre) criteria.uniqueResult();
    }

    @Transactional
    public void save(Genre genre) {
        sessionFactory.getCurrentSession().save(genre);
    }
}
