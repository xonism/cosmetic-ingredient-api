package cosmeticingredientapi.controllers;

import cosmeticingredientapi.models.SafetyLevel;
import cosmeticingredientapi.repositories.SafetyLevelRepository;
import cosmeticingredientapi.services.SafetyLevelsService;
import cosmeticingredientapi.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(value = SafetyLevelsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SafetyLevelsControllerTests {

    private static final Integer SAFETY_LEVEL_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SafetyLevelRepository safetyLevelRepository;

    @MockBean
    private SafetyLevelsService safetyLevelsService;

    @Test
    void givenSafetyLevel_whenGetSafetyLevelById_thenSafetyLevelIsReturned() throws Exception {
        SafetyLevel mockSafetyLevel = new SafetyLevel();
        mockSafetyLevel.setId(SAFETY_LEVEL_ID);
        mockSafetyLevel.setName("Safe");

        when(safetyLevelsService.getById(SAFETY_LEVEL_ID)).thenReturn(mockSafetyLevel);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/v1/safety-levels/" + SAFETY_LEVEL_ID);

        MvcResult actualResult = mockMvc.perform(requestBuilder).andReturn();

        assertThat(actualResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(JsonUtils.getObjectAsJsonString(mockSafetyLevel))
                .isEqualTo(actualResult.getResponse().getContentAsString());
    }

    @Test
    void givenNonExistentSafetyLevel_whenGetSafetyLevelById_thenNotFoundIsReturned() throws Exception {
        when(safetyLevelRepository.findById(0L)).thenReturn(Optional.empty());

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/v1/safety-levels/0");

        MvcResult actualResult = mockMvc.perform(requestBuilder).andReturn();

        assertThat(actualResult.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
