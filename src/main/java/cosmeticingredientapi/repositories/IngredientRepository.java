package cosmeticingredientapi.repositories;

import cosmeticingredientapi.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
