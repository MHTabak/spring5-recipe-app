package guru.springframework.services;

// Lecture 108 Initial version

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

}
