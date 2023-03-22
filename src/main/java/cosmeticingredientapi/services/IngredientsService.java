package cosmeticingredientapi.services;

import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.IngredientRequest;
import cosmeticingredientapi.repositories.IngredientRepository;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.ResponseUtils;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                ingredientsRepository.findAll(SortUtils.SORT_ID_ASC),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> getIngredient(Long id) {
        Optional<Ingredient> ingredientById = ingredientsRepository.findById(id);
        if (ingredientById.isPresent()) {
            Ingredient ingredient = ingredientById.get();
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } else {
            return ResponseUtils.createNotFoundByIdErrorResponseEntity("Ingredient", id);
        }
    }

    public ResponseEntity<Object> createIngredient(IngredientRequest ingredientRequest) {
        if (ingredientRequest.name() == null) {
            return ResponseUtils.createMustNotBeNullResponseEntity("Ingredient name");
        }

        SafetyLevel safetyLevel = new SafetyLevel();
        long safetyLevelId = ingredientRequest.safetyLevelId();
        safetyLevel.setId(safetyLevelId);
        safetyLevelRepository.findById(safetyLevelId)
                .ifPresent(resultSafetyLevel -> safetyLevel.setName(resultSafetyLevel.getName()));

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequest.name().trim().toLowerCase());
        ingredient.setSafetyLevel(safetyLevel);

        return new ResponseEntity<>(
                ingredientsRepository.save(ingredient),
                HttpStatus.CREATED);
    }
}
