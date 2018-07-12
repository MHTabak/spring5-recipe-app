package guru.springframework.domain;

// Created in lecture 103

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // This is mapped by the categories attribute in the Recipe class. Use a set to
    //    enforce uniqueness
    // Lecture 108 Must initialize set before adding to it or get exception
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recepies;

}
