package guru.springframework.domain;

// Created Lecture 97
// Has a One:One relations with Recipe entity
// Lecture 114 Ran Lombok, then Delombok for @Data on this file. This gave us hascode(), isEquail(),
//    canEqual() and toString()
// Lecture 118 Refactor for Lombok. Add @Data, remove getters/setters constructor, toString, equals
//    hashCode and canEqual.
// Lecture 119 Exclude recipe from equals and hashCode

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // No cascade type on recipe. If we delete notes, we don't want to delete the recipe
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
