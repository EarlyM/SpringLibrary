package ua.library.dao.interfaces;

import ua.library.model.entities.Genre;

import java.util.List;

public interface GenreDAO {
    List<Genre> getGenres();
    Genre getGenreByName(String genre);
    void save(Genre genre);
}
