package org.codewithcaleb.spring_batch_001.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book_table")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String yearOfPublishing;
}
