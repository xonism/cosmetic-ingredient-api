package cosmeticingredientapi.records;

import java.util.Objects;

public record IngredientCreateRequest(String name, Long safetyLevelId) {

    public IngredientCreateRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(safetyLevelId);
    }
}
