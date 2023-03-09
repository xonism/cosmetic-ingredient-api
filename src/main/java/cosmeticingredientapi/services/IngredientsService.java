package cosmeticingredientapi.services;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.Error;
import cosmeticingredientapi.repositories.IngredientRepository;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    private final IngredientRepository ingredientsRepository;
    private final SafetyLevelRepository safetyLevelRepository;

    public IngredientsService(IngredientRepository ingredientsRepository, SafetyLevelRepository safetyLevelRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.safetyLevelRepository = safetyLevelRepository;
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

        boolean doesSafetyLevelIdExist = safetyLevelRepository.findById(ingredient.getSafetyLevelId()).isPresent();
        if (!doesSafetyLevelIdExist) {
            return new ResponseEntity<>(
                    new Error(String.format("Safety level ID '%s' does not exist", ingredient.getSafetyLevelId())),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                ingredientsRepository.save(ingredient),
                HttpStatus.CREATED
        );
    }
}
