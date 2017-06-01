package ua.library.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.library.dao.GenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAOImpl<K, T extends Serializable> implements GenericDAO<K, T> {

    private Class type;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDAOImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        type = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public void save(K entity) {
        getSession().save(entity);
    }

    @Override
    public void delete(K enquiry) {
        getSession().delete(enquiry);
    }

    @Override
    public void update(K entity) {
        getSession().update(entity);
    }

    @Override
    public K find(T enquiry) {
        return (K) getSession().get(type, enquiry);
    }

    @Override
    public List<K> findAll() {
        return getSession().createCriteria(type).list();
    }

    @Override
    public K getByName(String name) {
        Criteria criteria = getSession().createCriteria(type);
        criteria.add(Restrictions.ilike("name", name));
        return (K) criteria.uniqueResult();
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
