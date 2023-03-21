package cosmeticingredientapi.records;

import lombok.Getter;

@Getter
public record IngredientRequest(String name, long safetyLevelId) {
}
