package cosmeticingredientapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private JsonUtils() {

    }

    public static String getObjectAsJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            ExceptionUtils.throwActualException(exception);
        }
        return null;
    }
}
