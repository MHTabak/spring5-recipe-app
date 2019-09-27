package guru.springframework.services;

// Created Lecture 108
// Lecture 115 - Add Lombok @Slf4j
// Lecture 200 - Add findById(Long)
// Lecture 204 - Add RecipeCommandToRecipe and RecipeToRecipeCommand
//               and Implement saveRecipeCommand()
// Lecture 206 - Add findCommandById()

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    // Use final when doing constructor initialization
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {

        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;

    }

    @Override
    public Set<Recipe> getRecipes() {

        log.debug("I'm in the RecipeServiceImpl.getRecipes method");

        Set<Recipe> recipeSet = new HashSet<>();

        // Java 8 syntax
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;

    }

    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }
        return recipeOptional.get();

    }

    // Calls above method
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        // Extract the Recipe from the RecipeCommand backing object
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        // Return a RecipeCommand to the web tier
        return recipeToRecipeCommand.convert(savedRecipe);
    }

}
