package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.records.IngredientUpdateRequest;
import cosmeticingredientapi.repositories.IngredientRepository;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {

    private static final String ENTITY_NAME = "Ingredient";

    private final IngredientRepository ingredientsRepository;
    private final SafetyLevelsService safetyLevelsService;

    public IngredientsService(IngredientRepository ingredientsRepository, SafetyLevelsService safetyLevelsService) {
        this.ingredientsRepository = ingredientsRepository;
        this.safetyLevelsService = safetyLevelsService;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientsRepository.findAll(SortUtils.SORT_ID_ASC);
    }

    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientsRepository.findById(id);
        if (ingredient.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        return ingredient.get();
    }

    public Ingredient createIngredient(IngredientCreateRequest ingredientCreateRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateRequest.name().trim().toLowerCase());

        SafetyLevel safetyLevel = safetyLevelsService.getSafetyLevelById(ingredientCreateRequest.safetyLevelId());
        ingredient.setSafetyLevel(safetyLevel);

        return ingredientsRepository.save(ingredient);
    }

    public Ingredient updateIngredient(IngredientUpdateRequest ingredientUpdateRequest) {
        Ingredient ingredient = getIngredientById(ingredientUpdateRequest.id());
        ingredient.setName(ingredientUpdateRequest.name().trim().toLowerCase());

        SafetyLevel safetyLevel = safetyLevelsService.getSafetyLevelById(ingredientUpdateRequest.safetyLevelId());
        ingredient.setSafetyLevel(safetyLevel);

        return ingredientsRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientsRepository.deleteById(id);
    }
}
