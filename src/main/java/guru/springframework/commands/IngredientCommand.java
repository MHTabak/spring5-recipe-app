package guru.springframework.commands;

// Created in Lecture 204 - Uses project Lombok
// Lecture 206 - Change unitOfMeasure to uom
// Lecture 209 - Add property recipeId


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by jt on 6/21/17.
 */

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}