package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.services.SafetyLevelsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/safety-levels")
public class SafetyLevelsController {

    private final SafetyLevelsService safetyLevelsService;

    public SafetyLevelsController(SafetyLevelsService safetyLevelsService) {
        this.safetyLevelsService = safetyLevelsService;
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<SafetyLevel>> getAllSafetyLevels() {
        return new ResponseEntity<>(
                safetyLevelsService.getAllSafetyLevels(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<SafetyLevel> getSafetyLevel(@PathVariable Long id) {
        return new ResponseEntity<>(
                safetyLevelsService.getSafetyLevelById(id),
                HttpStatus.OK);
    }
}
