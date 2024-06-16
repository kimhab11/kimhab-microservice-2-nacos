package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

@Table(name = "authors")
@Entity
@Data
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books = new HashSet<>();
}
