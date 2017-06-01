package ua.library.dao.impl;

import org.springframework.stereotype.Repository;
import ua.library.model.entities.Publisher;
import ua.library.dao.PublisherDAO;

@Repository("PublisherDAO")
public class PublisherDAOImpl extends GenericDAOImpl<Publisher, Long> implements PublisherDAO {

}
