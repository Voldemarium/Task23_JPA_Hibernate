package Stepanov.homework.Bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "ordering")
@Getter
@Setter
@RequiredArgsConstructor
public class Ordering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "ordering", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<OrderingDetails> orderingDetailsList;

    @Column
    private Integer purchase_amount;

    @PrePersist
    @PreUpdate
    @PostLoad
    private void postLoad() {
        this.purchase_amount = orderingDetailsList.stream()
                .map(o -> (o.getQuantity() * o.getBook().getPrice()))
                .reduce(Integer::sum).orElse(null);
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", buyer=" + buyer.getId() +
                ", orderingDetailsList=" + orderingDetailsList +
                ", purchase_amount=" + purchase_amount +
                '}';
    }
}
