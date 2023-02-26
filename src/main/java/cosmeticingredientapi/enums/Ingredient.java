package cosmeticingredientapi.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Ingredient {
    SESAMUM_INDICUM_SEED_OIL(1L, "sesamum indicum seed oil", SafetyLevel.SAFE),
    HELIANTHUS_ANNUUS_SEED_OIL(2L, "helianthus annuus seed oil", SafetyLevel.SAFE),
    HYPERICUM_PERFORATUM_FLOWER_EXTRACT(3L, "hypericum perforatum flower extract", SafetyLevel.MEDIUM);

    private final Long id;
    private final String name;
    private final SafetyLevel safetyLevel;

    Ingredient(Long id, String name, SafetyLevel safetyLevel) {
        this.id = id;
        this.name = name;
        this.safetyLevel = safetyLevel;
    }

    public static List<String> getAllIngredientEnumNames() {
        return Arrays.stream(Ingredient.values()).map(Ingredient::getName).toList();
    }
}
