package cosmeticingredientapi.repositories;

import cosmeticingredientapi.models.SafetyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyLevelRepository extends JpaRepository<SafetyLevel, Long> {
}
