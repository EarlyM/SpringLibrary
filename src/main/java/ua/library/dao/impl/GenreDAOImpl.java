package ua.library.dao.impl;

import org.springframework.stereotype.Repository;
import ua.library.model.entities.Genre;
import ua.library.dao.GenreDAO;

@Repository("GenreDAO")
public class GenreDAOImpl extends GenericDAOImpl<Genre, Long> implements GenreDAO {

}
