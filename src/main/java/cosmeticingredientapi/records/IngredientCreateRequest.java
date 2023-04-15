package cosmeticingredientapi.records;

import java.util.Objects;

public record IngredientCreateRequest(String name, String safetyLevelName) {

    public IngredientCreateRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(safetyLevelName);
    }
}
