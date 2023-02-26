package cosmeticingredientapi.records;

public record IngredientInfo (Long id, String name, String safetyLevel) {
    public static IngredientInfo getObjectWithNullIdAndSafetyLevel(String ingredientName) {
        return new IngredientInfo(null, ingredientName, null);
    }
}
