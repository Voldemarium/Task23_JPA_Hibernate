package Stepanov.homework.Bookstore.ordering;

import Stepanov.homework.Bookstore.entity.Ordering;
import Stepanov.homework.Bookstore.entity.OrderingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderingDao {
    private final SessionFactory sessionFactory;

    public OrderingDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Ordering createOrdering(Ordering ordering) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ordering);
            transaction.commit();
            return ordering;
        }
    }

    public List<Ordering> getOrdering() {
        try(Session session = sessionFactory.openSession()) {
            Query<Ordering> query = session.createQuery("from Ordering", Ordering.class);
            return query.getResultList();
        }
    }

    public Ordering findOrdering(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Ordering.class, id);
        }
    }
}
