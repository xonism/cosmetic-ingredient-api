package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.models.Ingredient;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.IngredientCreateRequest;
import cosmeticingredientapi.records.IngredientRequest;
import cosmeticingredientapi.records.IngredientUpdateRequest;
import cosmeticingredientapi.repositories.IngredientRepository;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class IngredientsService {

    private static final String ENTITY_NAME = "Ingredient";

    private final IngredientRepository ingredientsRepository;
    private final SafetyLevelsService safetyLevelsService;

    public IngredientsService(IngredientRepository ingredientsRepository, SafetyLevelsService safetyLevelsService) {
        this.ingredientsRepository = ingredientsRepository;
        this.safetyLevelsService = safetyLevelsService;
    }

    public List<Ingredient> getAll() {
        return ingredientsRepository.findAll(SortUtils.SORT_ID_ASC);
    }

    public Ingredient getById(long id) {
        Optional<Ingredient> ingredient = ingredientsRepository.findById(id);
        if (ingredient.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        return ingredient.get();
    }

    public Ingredient create(IngredientCreateRequest ingredientCreateRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateRequest.name().trim().toLowerCase());

        SafetyLevel safetyLevel = safetyLevelsService.getByName(ingredientCreateRequest.safetyLevelName());
        ingredient.setSafetyLevel(safetyLevel);

        return ingredientsRepository.save(ingredient);
    }

    public Ingredient update(IngredientUpdateRequest ingredientUpdateRequest) {
        Ingredient ingredient = getById(ingredientUpdateRequest.id());
        ingredient.setName(ingredientUpdateRequest.name().trim().toLowerCase());

        SafetyLevel safetyLevel = safetyLevelsService.getByName(ingredientUpdateRequest.safetyLevelName());
        ingredient.setSafetyLevel(safetyLevel);

        return ingredientsRepository.save(ingredient);
    }

    public void delete(long id) {
        ingredientsRepository.deleteById(id);
    }

    public List<Ingredient> getWithSafetyLevels(IngredientRequest ingredientRequest) {
        List<String> ingredientList = Stream.of(ingredientRequest.ingredients().split(","))
                .map(ingredient -> ingredient.contains("/")
                        ? Arrays.stream(ingredient.split("/")).toList().get(0)
                        : ingredient)
                .toList();

        return ingredientList.stream()
                .map(ingredientName -> {
                    if (ingredientsRepository.existsByName(ingredientName)) {
                        return ingredientsRepository.findByName(ingredientName);
                    }

                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(ingredientName);

                    SafetyLevel safetyLevel = safetyLevelsService.getByName(
                            cosmeticingredientapi.enums.SafetyLevel.UNCATEGORIZED.name());
                    ingredient.setSafetyLevel(safetyLevel);

                    return ingredientsRepository.save(ingredient);
                })
                .toList();
    }
}
