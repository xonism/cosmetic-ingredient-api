package cosmeticingredientapi.repositories;

import cosmeticingredientapi.models.SafetyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafetyLevelRepository extends JpaRepository<SafetyLevel, Long> {
}
