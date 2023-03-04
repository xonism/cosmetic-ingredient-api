package cosmeticingredientapi.services;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.Error;
import cosmeticingredientapi.repositories.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    private final IngredientRepository ingredientsRepository;

    public IngredientsService(IngredientRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(
                ingredientsRepository.findAll(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> createIngredient(Ingredient ingredient) {
        ingredient.setName(ingredient.getName().trim().toLowerCase());

        boolean doesIngredientExist = ingredientsRepository.existsByName(ingredient.getName());
        if (doesIngredientExist) {
            return new ResponseEntity<>(
                    new Error(String.format("Ingredient '%s' already exists", ingredient.getName())),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                ingredientsRepository.save(ingredient),
                HttpStatus.CREATED
        );
    }
}
