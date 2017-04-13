package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.library.dao.interfaces.PublisherDAO;
import ua.library.model.entities.Publisher;

@Repository
public class PublisherDAOImpl implements PublisherDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Publisher findPublisher(String publisher) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Publisher.class);
        criteria.add(Restrictions.ilike("name", publisher));
        return (Publisher) criteria.uniqueResult();
    }

    public void save(Publisher publisher) {
        sessionFactory.getCurrentSession().save(publisher);
    }
}
