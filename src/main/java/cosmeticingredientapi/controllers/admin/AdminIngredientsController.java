package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.services.IngredientsService;
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

    @PostMapping
    public ResponseEntity<Object> createIngredient(@RequestBody IngredientCreateRequest ingredientCreateRequest) {
        return ingredientsService.createIngredient(ingredientCreateRequest);
    }
}
