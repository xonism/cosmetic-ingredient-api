package cosmeticingredientapi.records;

import java.time.ZonedDateTime;

public record Error (Object error, ZonedDateTime timestamp) {
}
