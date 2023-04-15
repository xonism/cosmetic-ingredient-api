package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.exceptions.IdMismatchException;
import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.records.IngredientUpdateRequest;
import cosmeticingredientapi.services.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/admin/ingredients",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminIngredientsController {

    private final IngredientsService ingredientsService;

    public AdminIngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientCreateRequest ingredientCreateRequest) {
        return new ResponseEntity<>(
                ingredientsService.create(ingredientCreateRequest),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable Long id,
            @RequestBody IngredientUpdateRequest ingredientUpdateRequest
    ) {
        if (!ingredientUpdateRequest.id().equals(id)) {
            throw new IdMismatchException();
        }

        return new ResponseEntity<>(
                ingredientsService.update(ingredientUpdateRequest),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteIngredient(
            @PathVariable Long id
    ) {
        ingredientsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
