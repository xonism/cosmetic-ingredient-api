package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.exceptions.IdMismatchException;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelCreateRequest;
import cosmeticingredientapi.records.SafetyLevelUpdateRequest;
import cosmeticingredientapi.services.SafetyLevelsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<SafetyLevel> createSafetyLevel(
            @RequestBody SafetyLevelCreateRequest safetyLevelCreateRequest
    ) {
        return new ResponseEntity<>(
                safetyLevelsService.create(safetyLevelCreateRequest),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SafetyLevel> updateSafetyLevel(
            @PathVariable Long id,
            @RequestBody SafetyLevelUpdateRequest safetyLevelUpdateRequest
    ) {
        if (!safetyLevelUpdateRequest.id().equals(id)) {
            throw new IdMismatchException();
        }

        return new ResponseEntity<>(
                safetyLevelsService.update(safetyLevelUpdateRequest),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteSafetyLevel(@PathVariable Long id) {
        safetyLevelsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
