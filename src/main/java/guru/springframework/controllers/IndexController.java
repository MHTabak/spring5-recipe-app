package guru.springframework.controllers;

// Created in Lecture 82
// Lecture 106 - Inject Category and UnitOfMeasure repositories and test them
// Lecture 108 - Inject RecipeService, remove CategoryRepository and UnitOfMeasureRepository, pass Model
//               into getIndexPage()
// Lecture 114 - Add Bootstrap @Slf4j and a log statement
// Lecture 183 - Add code to getIndexPage() to test the recipe attribute of controler



import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Map the index page to blank, / or /index
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting index page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";

    }

}
