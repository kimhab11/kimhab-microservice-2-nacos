package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

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


  //  @ToString.Exclude
//  @JsonBackReference
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<BookEntity> books = new HashSet<>();
}
