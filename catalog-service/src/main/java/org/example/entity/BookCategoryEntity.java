package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_categories")
@Data
public class BookCategoryEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
