package Stepanov.homework.Bookstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(nullable = false)
    private Author author;

    @Column
    private Integer year_of_publication;

    @Column
    private Integer pages;

    @Column(nullable = false)
    private Integer price;

}
