package Stepanov.homework.Bookstore.bayer;

import Stepanov.homework.Bookstore.entity.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerDao {
    private final SessionFactory sessionFactory;

    public BuyerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createDDL(String queryDDL) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(queryDDL).executeUpdate();
            transaction.commit();
        }
    }

    public Buyer createBuyer(Buyer buyer) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(buyer);
            transaction.commit();
            return buyer;
        }
    }


    public Buyer findBuyer(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Buyer.class, id);
        }
    }
}
