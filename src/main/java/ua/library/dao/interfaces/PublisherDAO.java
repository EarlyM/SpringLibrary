package ua.library.dao.interfaces;

import ua.library.model.entities.Publisher;

public interface PublisherDAO {
    Publisher findPublisher(String publisher);
    void save(Publisher publisher);
}
