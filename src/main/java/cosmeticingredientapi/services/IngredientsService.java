package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.exceptions.NullNameException;
import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.repositories.IngredientRepository;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService {
    private static final String ENTITY_NAME = "Ingredient";

    private final IngredientRepository ingredientsRepository;
    private final SafetyLevelRepository safetyLevelRepository;

    public IngredientsService(IngredientRepository ingredientsRepository, SafetyLevelRepository safetyLevelRepository) {
        this.ingredientsRepository = ingredientsRepository;
        this.safetyLevelRepository = safetyLevelRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientsRepository.findAll(SortUtils.SORT_ID_ASC);
    }

    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredientById = ingredientsRepository.findById(id);
        if (ingredientById.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        return ingredientById.get();
    }

    public Ingredient createIngredient(IngredientCreateRequest ingredientCreateRequest) {
        if (ingredientCreateRequest.name() == null) {
            throw new NullNameException(ENTITY_NAME);
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateRequest.name().trim().toLowerCase());

        long safetyLevelId = ingredientCreateRequest.safetyLevelId();
        safetyLevelRepository.findById(safetyLevelId)
                .ifPresent(ingredient::setSafetyLevel);

        return ingredientsRepository.save(ingredient);
    }
}
