package cosmeticingredientapi.services;

import cosmeticingredientapi.exceptions.NotFoundByIdException;
import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.records.SafetyLevelCreateRequest;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SafetyLevelsServiceTests {

    private static final long SAFETY_LEVEL_ID = 1;

    @Mock
    private SafetyLevelRepository safetyLevelRepository;

    @InjectMocks
    private SafetyLevelsService safetyLevelsService;

    @Test
    void givenSafetyLevel_whenGetSafetyLevelById_thenSafetyLevelIsReturned() {
        SafetyLevel mockSafetyLevel = SafetyLevel.builder().id(SAFETY_LEVEL_ID).name("Safe").build();

        when(safetyLevelRepository.findById(SAFETY_LEVEL_ID)).thenReturn(Optional.of(mockSafetyLevel));

        SafetyLevel safetyLevel = safetyLevelsService.getById(SAFETY_LEVEL_ID);

        assertThat(safetyLevel).isNotNull().isEqualTo(mockSafetyLevel);

        verify(safetyLevelRepository).findById(SAFETY_LEVEL_ID); // for every when
        verifyNoMoreInteractions(safetyLevelRepository); // for every mock
    }

    @Test
    void givenNonExistentSafetyLevel_whenGetSafetyLevelById_thenExceptionIsThrown() {
        when(safetyLevelRepository.findById(SAFETY_LEVEL_ID)).thenReturn(Optional.empty());

        NotFoundByIdException notFoundByIdException =
                catchThrowableOfType(() -> safetyLevelsService.getById(SAFETY_LEVEL_ID), NotFoundByIdException.class);

        String expectedExceptionMessage = new NotFoundByIdException("Safety level").getMessage();

        assertThat(notFoundByIdException.getMessage())
                .isNotNull()
                .isEqualTo(expectedExceptionMessage);
    }

    @Test
    void givenSafetyLevel_whenCreateSafetyLevel_thenSafetyLevelIsReturned() {
        SafetyLevel safetyLevel = SafetyLevel.builder().id(SAFETY_LEVEL_ID).name("not safe  ").build();
        when(safetyLevelRepository.save(Mockito.any(SafetyLevel.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        SafetyLevelCreateRequest safetyLevelCreateRequest = new SafetyLevelCreateRequest("Safe");
        SafetyLevel createdSafetyLevel = safetyLevelsService.create(safetyLevelCreateRequest);
        ArgumentCaptor<SafetyLevel> safetyLevelArgumentCaptor = ArgumentCaptor.forClass(SafetyLevel.class);
        verify(safetyLevelRepository).save(safetyLevelArgumentCaptor.capture());
        SafetyLevel captured = safetyLevelArgumentCaptor.getValue();
        assertEquals("notsafe", captured.getName());
        assertThat(createdSafetyLevel).isNotNull().isEqualTo(safetyLevel);
        // TODO: ^
    }
}
