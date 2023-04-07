package cosmeticingredientapi.records;

import java.util.Objects;

public record IngredientUpdateRequest(Long id, String name, Long safetyLevelId) {

    public IngredientUpdateRequest {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(safetyLevelId);
    }
}
