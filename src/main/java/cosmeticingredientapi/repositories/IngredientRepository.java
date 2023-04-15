package cosmeticingredientapi.repositories;

import cosmeticingredientapi.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    boolean existsByName(String name);

    Ingredient findByName(String name);
}
