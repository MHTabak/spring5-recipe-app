package guru.springframework.controllers;

// Created in Lecture 200
// Lecture 205 - Add method newRecipe() to provide a backing object for the new recipe form
//               and method saveOrUpdate() to handle creating/modifying recipes
// Lecture 206 - Change URL of showById from "/recipe/show/{id}" to "/recipe/{id}/show"
//               Add updateRecipe()
// Lecture 207 - Add method

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    // Creat a new RecipeCommand as a backing object and return to the recipeform view
    // The name of the backing object is "recipe" to match what the form expects
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    // Update a recipe by id
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    // Handle POST of a new recipe. This does saving and updating depending on whether
    // id is null or not
    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }
}
