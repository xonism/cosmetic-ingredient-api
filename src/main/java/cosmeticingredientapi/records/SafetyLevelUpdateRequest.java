package cosmeticingredientapi.records;

import java.util.Objects;

public record SafetyLevelUpdateRequest(long id, String name) {

    public SafetyLevelUpdateRequest {
        Objects.requireNonNull(name);
    }
}
