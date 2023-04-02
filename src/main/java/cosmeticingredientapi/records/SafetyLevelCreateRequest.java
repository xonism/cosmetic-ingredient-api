package cosmeticingredientapi.records;

import java.util.Objects;

public record SafetyLevelCreateRequest(String name) {

    public SafetyLevelCreateRequest {
        Objects.requireNonNull(name);
    }
}
