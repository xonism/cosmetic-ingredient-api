package cosmeticingredientapi.records;

import java.time.ZonedDateTime;

public record Error (String error, ZonedDateTime timestamp) {
}
