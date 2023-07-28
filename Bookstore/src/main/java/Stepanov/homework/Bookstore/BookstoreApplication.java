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



@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookstoreApplication.class, args);

        OrderingDao orderingDao = context.getBean(OrderingDao.class);
        Ordering ordering1 = orderingDao.findOrdering(1L);
        log.info("{}", ordering1);

        BuyerDao buyerDao = context.getBean(BuyerDao.class);
        Buyer buyer = buyerDao.findBuyer(1L);

//        Ordering ordering2 = new Ordering();
//        ordering2.setId(2L);
//        ordering2.setBuyer(buyer);
//        orderingDao.createOrdering(ordering2);

        BookDAO bookDAO = context.getBean(BookDAO.class);
        Book book1 = bookDAO.findBook(1L);
        Book book2 = bookDAO.findBook(2L);


        OrderingDetails orderingDetails1= new OrderingDetails();
       orderingDetails1.setId(1L);
        orderingDetails1.setOrdering(ordering1);
        orderingDetails1.setBook(book1);
        orderingDetails1.setQuantity(2);

        OrderingDetails orderingDetails2= new OrderingDetails();
       orderingDetails2.setId(2L);
        orderingDetails2.setOrdering(ordering1);
        orderingDetails2.setBook(book2);
        orderingDetails2.setQuantity(3);


        Ordering_detailsDAO orderingDetailsDAO = context.getBean(Ordering_detailsDAO.class);
        orderingDetailsDAO.createOrdering_details(orderingDetails1);
        orderingDetailsDAO.createOrdering_details(orderingDetails2);

        Ordering ordering = orderingDao.findOrdering(1L);

        log.info("{}", ordering);
    }
}
