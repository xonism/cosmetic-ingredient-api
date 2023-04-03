package cosmeticingredientapi.records;

import java.util.Objects;

public record SafetyLevelUpdateRequest(Long id, String name) {

    public SafetyLevelUpdateRequest {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
    }
}
