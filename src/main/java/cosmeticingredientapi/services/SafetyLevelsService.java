package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelCreateRequest;
import cosmeticingredientapi.records.SafetyLevelUpdateRequest;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SafetyLevelsService {

    private static final String ENTITY_NAME = "Safety level";

    private final SafetyLevelRepository safetyLevelRepository;

    public SafetyLevelsService(SafetyLevelRepository safetyLevelRepository) {
        this.safetyLevelRepository = safetyLevelRepository;
    }

    public List<SafetyLevel> getAllSafetyLevels() {
        return safetyLevelRepository.findAll(SortUtils.SORT_ID_ASC);
    }

    public SafetyLevel getSafetyLevelById(Long id) {
        Optional<SafetyLevel> safetyLevelById = safetyLevelRepository.findById(id);
        if (safetyLevelById.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        return safetyLevelById.get();
    }

    public SafetyLevel getUncategorizedSafetyLevel() {
        return safetyLevelRepository.findByName(cosmeticingredientapi.enums.SafetyLevel.UNCATEGORIZED.name());
    }

    public SafetyLevel createSafetyLevel(SafetyLevelCreateRequest safetyLevelCreateRequest) {
        SafetyLevel safetyLevel = new SafetyLevel();
        safetyLevel.setName(safetyLevelCreateRequest.name().trim().toLowerCase());

        return safetyLevelRepository.save(safetyLevel);
    }

    public SafetyLevel updateSafetyLevel(SafetyLevelUpdateRequest safetyLevelUpdateRequest) {
        SafetyLevel safetyLevel = getSafetyLevelById(safetyLevelUpdateRequest.id());
        safetyLevel.setName(safetyLevelUpdateRequest.name().trim().toLowerCase());

        return safetyLevelRepository.save(safetyLevel);
    }

    public void deleteSafetyLevel(Long id) {
        safetyLevelRepository.deleteById(id);
    }
}
