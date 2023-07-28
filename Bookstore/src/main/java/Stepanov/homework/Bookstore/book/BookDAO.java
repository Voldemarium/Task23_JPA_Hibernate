package Stepanov.homework.Bookstore.book;

import Stepanov.homework.Bookstore.entity.Book;
import Stepanov.homework.Bookstore.entity.Ordering;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {
    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Book findBook(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        }
    }
}
