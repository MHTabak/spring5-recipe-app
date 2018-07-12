package guru.springframework.domain;

// Created lecture 98
// Lecture 98 Set up inverse side of relationship to Recipe
// Lecture 101 Add UnitOfMeasure
// Lecture 109

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

}
