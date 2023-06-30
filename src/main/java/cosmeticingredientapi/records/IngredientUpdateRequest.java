package cosmeticingredientapi.records;

import java.util.Objects;

public record IngredientUpdateRequest(long id, String name, String safetyLevelName) {

    public IngredientUpdateRequest {
        Objects.requireNonNull(name);
        Objects.requireNonNull(safetyLevelName);
    }
}
