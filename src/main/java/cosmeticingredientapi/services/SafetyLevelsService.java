package cosmeticingredientapi.services;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.Error;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.utils.SortUtils;
import cosmeticingredientapi.utils.TimeUtils;
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
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> getSafetyLevel(Long id) {
        Optional<SafetyLevel> safetyLevelById = safetyLevelRepository.findById(id);
        if (safetyLevelById.isPresent()) {
            SafetyLevel safetyLevel = safetyLevelById.get();
            return new ResponseEntity<>(safetyLevel, HttpStatus.OK);
        } else {
            String message = String.format("Safety level with ID %s not found", id);
            return new ResponseEntity<>(
                    new Error(message, TimeUtils.getTimestamp()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}