package Stepanov.homework.Bookstore.book;

import Stepanov.homework.Bookstore.BookstoreApplication;
import Stepanov.homework.Bookstore.entity.Book;
import Stepanov.homework.Bookstore.entity.Ordering;
import Stepanov.homework.Bookstore.entity.OrderingDetails;
import Stepanov.homework.Bookstore.ordering.OrderingDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {
    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Book findBook(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        }
    }

    public Book updateBook(Book book) {
//        try здесь используется автоматич закрытия сессии (AutoClosable)
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            //Список orderingDetails, прикрепленных к Book
            List<OrderingDetails> orderingDetailsBook = book.getOrderingDetails();

            for (OrderingDetails orderingDetail : orderingDetailsBook) {
                //Изменяем новое значение Book в каждом orderingDetail, в котором содержится Book
                orderingDetail.setBook(book);
                // Обновляем orderingDetail (обновление ordering, к которому он прикреплен произойдет каскадом)
                session.update(orderingDetail);
            }

            // Обновляем Book
            session.update(book);

            transaction.commit();
            return book;
        }
    }
}
