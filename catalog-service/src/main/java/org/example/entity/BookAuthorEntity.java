package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_authors")
@Data
public class BookAuthorEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

}
