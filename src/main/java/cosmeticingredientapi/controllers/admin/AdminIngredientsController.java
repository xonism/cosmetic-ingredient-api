package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/ingredients")
public class AdminIngredientsController {

    private final IngredientsService ingredientsService;

    public AdminIngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientCreateRequest ingredientCreateRequest) {
        return new ResponseEntity<>(
                ingredientsService.createIngredient(ingredientCreateRequest),
                HttpStatus.CREATED);
    }
}
