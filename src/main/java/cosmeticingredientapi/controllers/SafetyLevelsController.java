package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelRequest;
import cosmeticingredientapi.services.SafetyLevelsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/safety-levels")
public class SafetyLevelsController {
    private final SafetyLevelsService safetyLevelsService;

    public SafetyLevelsController(SafetyLevelsService safetyLevelsService) {
        this.safetyLevelsService = safetyLevelsService;
    }

    @GetMapping
    public ResponseEntity<List<SafetyLevel>> getAllSafetyLevels() {
        return safetyLevelsService.getAllSafetyLevels();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getSafetyLevel(@PathVariable Long id) {
        return safetyLevelsService.getSafetyLevel(id);
    }

    @PostMapping
    public ResponseEntity<Object> createSafetyLevel(@RequestBody SafetyLevelRequest safetyLevelRequest) {
        return safetyLevelsService.createSafetyLevel(safetyLevelRequest);
    }
}
