package cosmeticingredientapi.services;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelRequest;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.ResponseUtils;
import cosmeticingredientapi.utils.SortUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SafetyLevelsService {
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
        if (safetyLevelById.isPresent()) {
            SafetyLevel safetyLevel = safetyLevelById.get();
            return new ResponseEntity<>(safetyLevel, HttpStatus.OK);
        } else {
            return ResponseUtils.createNotFoundByIdErrorResponseEntity("Safety level", id);
        }
    }

    public ResponseEntity<Object> createSafetyLevel(SafetyLevelRequest safetyLevelRequest) {
        if (safetyLevelRequest.name() == null) {
            return ResponseUtils.createMustNotBeNullResponseEntity("Safety level name");
        }

        SafetyLevel safetyLevel = new SafetyLevel();
        safetyLevel.setName(safetyLevelRequest.name().trim().toLowerCase());

        return new ResponseEntity<>(
                safetyLevelRepository.save(safetyLevel),
                HttpStatus.CREATED);
    }
}
