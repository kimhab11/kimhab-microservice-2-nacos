package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "authors")
@Entity
@Getter
@Setter
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ToString.Exclude
    @JsonIgnore
//  @JsonBackReference
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<BookEntity> books = new HashSet<>();
}
