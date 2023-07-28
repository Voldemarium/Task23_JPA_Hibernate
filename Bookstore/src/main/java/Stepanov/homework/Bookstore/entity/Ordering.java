package Stepanov.homework.Bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "ordering", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<OrderingDetails> orderingDetailsList;


    @Transient
    private Integer purchase_amount;

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
