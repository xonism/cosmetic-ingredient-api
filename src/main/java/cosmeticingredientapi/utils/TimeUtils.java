package cosmeticingredientapi.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    private TimeUtils() {

    }

    public static ZonedDateTime getTimestamp() {
        ZoneId zoneId = ZoneId.of("UTC");
        return ZonedDateTime.now(zoneId);
    }
}
