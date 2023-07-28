package Stepanov.homework.Bookstore.ordering_details;

import Stepanov.homework.Bookstore.entity.OrderingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class Ordering_detailsDAO {
    private final SessionFactory sessionFactory;

    public Ordering_detailsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public OrderingDetails createOrdering_details(OrderingDetails orderingDetails) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orderingDetails);
//            String quantity = String.valueOf(orderingDetails.getQuantity());
//            String book_id = String.valueOf(orderingDetails.getBook().getId());
//            String price = String.valueOf(orderingDetails.getBook().getPrice());
//            String ordering_id = String.valueOf(orderingDetails.getOrdering().getId());
//            session.createNativeQuery("insert into ordering_details (quantity, book_id, price, ordering_id) values (" +
//                            quantity + ", " + book_id + ", " + price + ", "  + ordering_id + ")")
//                    .executeUpdate();
            transaction.commit();
            return orderingDetails;
        }
    }
}
