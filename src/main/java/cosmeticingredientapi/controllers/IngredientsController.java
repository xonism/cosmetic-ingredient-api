package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }

    @PostMapping
    public ResponseEntity<Object> createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientsService.createIngredient(ingredient);
    }
}