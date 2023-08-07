package Stepanov.homework.Bookstore;

import Stepanov.homework.Bookstore.bayer.BuyerDao;
import Stepanov.homework.Bookstore.book.BookDAO;
import Stepanov.homework.Bookstore.entity.Book;
import Stepanov.homework.Bookstore.entity.Buyer;
import Stepanov.homework.Bookstore.entity.Ordering;
import Stepanov.homework.Bookstore.entity.OrderingDetails;
import Stepanov.homework.Bookstore.ordering.OrderingDao;
import Stepanov.homework.Bookstore.ordering_details.Ordering_detailsDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        OrderingDao orderingDao = context.getBean(OrderingDao.class);
        Ordering ordering1 = orderingDao.findOrdering(1L);
        Ordering ordering2 = orderingDao.findOrdering(2L);
        log.info("{}", ordering1);

        BuyerDao buyerDao = context.getBean(BuyerDao.class);
        Buyer buyer = buyerDao.findBuyer(1L);

        BookDAO bookDAO = context.getBean(BookDAO.class);
        Book book1 = bookDAO.findBook(1L);
        Book book2 = bookDAO.findBook(2L);

        log.info("book1: {}", book1);
        log.info("book2: {}", book2);


        OrderingDetails orderingDetails1 = new OrderingDetails();
        orderingDetails1.setOrdering(ordering1);
        orderingDetails1.setBook(book1);
        orderingDetails1.setQuantity(2);

        OrderingDetails orderingDetails2 = new OrderingDetails();
        orderingDetails2.setOrdering(ordering1);
        orderingDetails2.setBook(book2);
        orderingDetails2.setQuantity(3);

        OrderingDetails orderingDetails3 = new OrderingDetails();
        orderingDetails3.setOrdering(ordering2);
        orderingDetails3.setBook(book1);
        orderingDetails3.setQuantity(1);

        OrderingDetails orderingDetails4 = new OrderingDetails();
        orderingDetails4.setOrdering(ordering2);
        orderingDetails4.setBook(book2);
        orderingDetails4.setQuantity(1);

        Ordering_detailsDAO orderingDetailsDAO = context.getBean(Ordering_detailsDAO.class);

////        orderingDetailsDAO.createDDL("ALTER TABLE ordering_details DROP CONSTRAINT fkiir7yrfqi6ir516xk7f1whlx8");
////        orderingDetailsDAO.createDDL("ALTER TABLE ordering_details ADD FOREIGN KEY (book_id, price) " +
////                "                              REFERENCES book (id, price) ON UPDATE CASCADE");


//        orderingDetailsDAO.createOrdering_details(orderingDetails1);
//        orderingDetailsDAO.createOrdering_details(orderingDetails2);
//        orderingDetailsDAO.createOrdering_details(orderingDetails3);
//        orderingDetailsDAO.createOrdering_details(orderingDetails4);


        book2.setPrice(20);
        bookDAO.updateBook(book2);

        List<Book> books = bookDAO.getBooks();
        log.info("books {}", books);

//
//        Ordering ordering = orderingDao.findOrdering(1L);
//
//        log.info("{}", ordering);
    }
}
