package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.exceptions.NullNameException;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelCreateRequest;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<SafetyLevel>> getAllSafetyLevels() {
        return new ResponseEntity<>(
                safetyLevelRepository.findAll(SortUtils.SORT_ID_ASC),
                HttpStatus.OK);
    }

    public ResponseEntity<Object> getSafetyLevel(Long id) {
        Optional<SafetyLevel> safetyLevelById = safetyLevelRepository.findById(id);
        if (safetyLevelById.isEmpty()) {
            throw new NotFoundByIdException(ENTITY_NAME);
        }
        SafetyLevel safetyLevel = safetyLevelById.get();
        return new ResponseEntity<>(safetyLevel, HttpStatus.OK);
    }

    public ResponseEntity<Object> createSafetyLevel(SafetyLevelCreateRequest safetyLevelCreateRequest) {
        if (safetyLevelCreateRequest.name() == null) {
            throw new NullNameException(ENTITY_NAME);
        }

        SafetyLevel safetyLevel = new SafetyLevel();
        safetyLevel.setName(safetyLevelCreateRequest.name().trim().toLowerCase());

        return new ResponseEntity<>(
                safetyLevelRepository.save(safetyLevel),
                HttpStatus.CREATED);
    }
}
