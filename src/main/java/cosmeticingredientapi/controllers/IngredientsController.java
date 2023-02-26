package cosmeticingredientapi.controllers;

import cosmeticingredientapi.enums.Ingredient;
import cosmeticingredientapi.records.IngredientInfo;
import cosmeticingredientapi.records.IngredientsRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class IngredientsController {
    @GetMapping("/ingredients")
    public List<IngredientInfo> getIngredients() {
        return convertIngridientEnumValuesToIngredientInfoList();
    }

    public List<IngredientInfo> convertIngridientEnumValuesToIngredientInfoList() {
        return Arrays.stream(Ingredient.values())
                .map(ingredient -> new IngredientInfo(
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getSafetyLevel().getValue()))
                .toList();
    }

    @PostMapping("/ingredients")
    public List<IngredientInfo> postIngredients(@RequestBody IngredientsRequest ingredientsRequest) {
        return getIngredientsWithSafetyLevel(ingredientsRequest);
    }

    public List<IngredientInfo> getIngredientsWithSafetyLevel(IngredientsRequest ingredientsRequest) {
        List<String> ingredientEnumNames = Ingredient.getAllIngredientEnumNames();

        List<String> requestIngredientNameList =
                Arrays.stream(getFormattedIngredientsString(ingredientsRequest).split(","))
                        .map(ingredientName -> ingredientName.trim().toLowerCase())
                        .toList();

        return requestIngredientNameList.stream()
                .map(requestIngredientName -> {
                    if (!ingredientEnumNames.contains(requestIngredientName)) {
                        return IngredientInfo.getObjectWithNullIdAndSafetyLevel(requestIngredientName);
                    }
                    Ingredient ingredient =
                            Ingredient.valueOf(requestIngredientName.toUpperCase().replace(" ", "_"));
                    return new IngredientInfo(
                            ingredient.getId(),
                            ingredient.getName(),
                            ingredient.getSafetyLevel().getValue());
                })
                .toList();
    }

    private String getFormattedIngredientsString(IngredientsRequest ingredientsRequest) {
        return ingredientsRequest.ingredients().replace("*", "");
    }
}
