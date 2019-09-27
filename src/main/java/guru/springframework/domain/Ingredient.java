package guru.springframework.domain;

// Created lecture 98
// Lecture 98 Set up inverse side of relationship to Recipe
// Lecture 101 Add UnitOfMeasure
// Lecture 118 Refactor for Lombok, add @Data removed getters/setters,
// Lecture 119 exclude recipe from hashCode and equals

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    // Lecture 101 - though default, make the fetch type eager to show intent
    // No cascading operations to UnitOfMeasure
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    // Recipe this ingredient belongs to. No cascading, don't want to delete a recipe
    // if we delete an ingredient
    @ManyToOne
    private Recipe recipe;

    public Ingredient() { }

    // Lecture 109 Add Constructor with description amound and UnitOfMeasure
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    // Lecture 109 Drop Recipe from Constructor
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
