package Stepanov.homework.Bookstore.ordering_details;

import Stepanov.homework.Bookstore.entity.Ordering;
import Stepanov.homework.Bookstore.entity.OrderingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class Ordering_detailsDAO {
    private final SessionFactory sessionFactory;

    public Ordering_detailsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createDDL(String queryDDL) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(queryDDL).executeUpdate();
            transaction.commit();
        }
    }

    public Serializable createOrdering_details(OrderingDetails orderingDetails) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Serializable id = session.save(orderingDetails);

            //обновляем Ordering
            Ordering ordering = orderingDetails.getOrdering();
            List<OrderingDetails> orderingDetailsList = ordering.getOrderingDetailsList();
            orderingDetailsList.add(orderingDetails);
            session.update(ordering);

            transaction.commit();
            return id;
        }
    }
}
