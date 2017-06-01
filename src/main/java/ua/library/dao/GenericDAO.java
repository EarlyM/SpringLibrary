package ua.library.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<K, T extends Serializable> {
    void save(K entity);
    void delete(K enquiry);
    void update(K entity);
    K find(T enquiry);
    List<K> findAll();
    K getByName(String name);

}
