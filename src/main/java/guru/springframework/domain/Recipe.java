package guru.springframework.domain;

// Created Lecture 97
// Lecture 98 Add One:Many relationship to Ingredient. Make directions an Lob
// Lecture 109 Modify the setNotes() method to add this Recipe to the notes passed in
//         and add method addIngredient()
// Lecture 115 - refactored to use Lombok. Removed all getters and setters except
//         setNotes() and addIngredient(). Add @Data to file

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    // Lecture 108 Make this an Lob or it will be treated as a 255 character String
    @Lob
    private String directions;

    // This class will own this relationship, persist all operations mapped by
    // the recipe attribute in the Ingredients class. Use a set to force uniqueness
    // Lecture 108 Must initialize Set for ingredients before trying to add any, or get exception
    @OneToMany(cascade = CascadeType.ALL, mappedBy="recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    // Added Lecture 102 Use EnumType.STRING so adding a new value to the Enum will
    // not affect the meaning of what is stored in the database. See notes
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // Recipe has cascadce type of ALL making it the owner of the relationship
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    // Added Lecture 103
    // Lecture 108 Must initialize Hashset before adding to it or get exception
    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories = new HashSet<>();

    // Lecture 109 Add this Recipe to the notes
    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    // Lecture 109 Convenience method. This method returns the updated object which is not strictly requried
    // but is good practice
    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
