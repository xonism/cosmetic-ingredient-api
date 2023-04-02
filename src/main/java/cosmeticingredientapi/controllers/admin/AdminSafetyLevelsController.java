package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelCreateRequest;
import cosmeticingredientapi.services.SafetyLevelsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/admin/safety-levels",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class AdminSafetyLevelsController {

    private final SafetyLevelsService safetyLevelsService;

    public AdminSafetyLevelsController(SafetyLevelsService safetyLevelsService) {
        this.safetyLevelsService = safetyLevelsService;
    }

    @PostMapping
    public ResponseEntity<SafetyLevel> createSafetyLevel(@RequestBody SafetyLevelCreateRequest safetyLevelCreateRequest) {
        return new ResponseEntity<>(
                safetyLevelsService.createSafetyLevel(safetyLevelCreateRequest),
                HttpStatus.CREATED);
    }
}
