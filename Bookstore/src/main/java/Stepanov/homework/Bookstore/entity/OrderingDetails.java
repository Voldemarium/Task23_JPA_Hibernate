package Stepanov.homework.Bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ordering_details")
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
public class OrderingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ordering ordering;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "book_id", referencedColumnName = "id"),
            @JoinColumn(name = "price", referencedColumnName = "price")
    })
    private Book book;

    @Column
    private Integer quantity;

    @Override
    public String toString() {
        return "OrderingDetails{" +
                "id=" + id +
                ", ordering=" + ordering.getId() +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
