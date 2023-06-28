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

    public List<SafetyLevel> getAll() {
        return safetyLevelRepository.findAll(SortUtils.SORT_ID_ASC);
    }

    public SafetyLevel getById(long id) {
        Optional<SafetyLevel> safetyLevelById = safetyLevelRepository.findById(id);
        if (safetyLevelById.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        return safetyLevelById.get();
    }

    public SafetyLevel getByName(String name) {
        return safetyLevelRepository.findByName(name);
    }

    public SafetyLevel create(SafetyLevelCreateRequest safetyLevelCreateRequest) {
        SafetyLevel safetyLevel = new SafetyLevel();
        safetyLevel.setName(safetyLevelCreateRequest.name().trim().toLowerCase());

        return safetyLevelRepository.save(safetyLevel);
    }

    public SafetyLevel update(SafetyLevelUpdateRequest safetyLevelUpdateRequest) {
        SafetyLevel safetyLevel = getById(safetyLevelUpdateRequest.id());
        safetyLevel.setName(safetyLevelUpdateRequest.name().trim().toLowerCase());

        return safetyLevelRepository.save(safetyLevel);
    }

    public void delete(long id) {
        safetyLevelRepository.deleteById(id);
    }
}
