package guru.springframework.controllers;

// Created in Lecture 82
// Lecture 106 Inject Category and UnitOfMeasure repositories and test them
// Lecture 108 Inject RecipeService, remove CategoryRepository and UnitOfMeasureRepository, pass Model
//    into getIndexPage()



import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Map the index page to blank, / or /index
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";

    }

}
