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
@RequestMapping(
        path = "/api/v1/safety-levels",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class SafetyLevelsController {

    private final SafetyLevelsService safetyLevelsService;

    public SafetyLevelsController(SafetyLevelsService safetyLevelsService) {
        this.safetyLevelsService = safetyLevelsService;
    }

    @GetMapping
    public ResponseEntity<List<SafetyLevel>> getAllSafetyLevels() {
        return new ResponseEntity<>(
                safetyLevelsService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SafetyLevel> getSafetyLevel(@PathVariable long id) {
        return new ResponseEntity<>(
                safetyLevelsService.getById(id),
                HttpStatus.OK);
    }
}
