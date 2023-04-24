package cosmeticingredientapi.records;

import java.util.Objects;

public record IngredientRequest(String ingredients) {

    public IngredientRequest {
        Objects.requireNonNull(ingredients);

        ingredients = ingredients.toLowerCase().trim()
                .replace("*", "")
                .replace(", ", ",");
    }
}
