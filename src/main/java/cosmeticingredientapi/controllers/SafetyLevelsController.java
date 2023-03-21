package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/safety-levels")
public class SafetyLevelsController {
    private final SafetyLevelRepository safetyLevelRepository;

    public SafetyLevelsController(SafetyLevelRepository safetyLevelRepository) {
        this.safetyLevelRepository = safetyLevelRepository;
    }

    @GetMapping
    public List<SafetyLevel> getAllSafetyLevels() {
        return safetyLevelRepository.findAll();
    }
}
