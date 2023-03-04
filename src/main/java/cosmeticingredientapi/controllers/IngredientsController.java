package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.repositories.IngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientsController {
    private final IngredientRepository ingredientsRepository;

    public IngredientsController(IngredientRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientsRepository.findAll();
    }
}
