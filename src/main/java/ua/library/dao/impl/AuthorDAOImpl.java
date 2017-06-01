package ua.library.dao.impl;

import org.springframework.stereotype.Repository;
import ua.library.model.entities.Author;
import ua.library.dao.AuthorDAO;

@Repository(value = "AuthorDAO")
public class AuthorDAOImpl extends GenericDAOImpl<Author, Long> implements AuthorDAO {

}
