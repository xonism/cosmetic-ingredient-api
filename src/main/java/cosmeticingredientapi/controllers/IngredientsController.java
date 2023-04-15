package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.IngredientRequest;
import cosmeticingredientapi.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/ingredients",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class IngredientsController {

    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(
                ingredientsService.getAllIngredients(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return new ResponseEntity<>(
                ingredientsService.getIngredientById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Ingredient>> getIngredientsWithSafetyLevels(
            @RequestBody IngredientRequest ingredientRequest
    ) {
        return new ResponseEntity<>(
                ingredientsService.getIngredientsWithSafetyLevels(ingredientRequest),
                HttpStatus.OK);
    }
}
