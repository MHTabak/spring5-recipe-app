package guru.springframework.services;

// Lecture 108 Initial version
// Lecture 200 - Add findById()
// Lecture 204 - Add saveRecipeCommand()
// Lecture 206 - Add findCommandById()

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
