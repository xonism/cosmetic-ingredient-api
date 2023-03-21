package cosmeticingredientapi.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    private TimeUtils() {

    }

    public static ZonedDateTime getTimestamp() {
        ZoneId zoneId = ZoneId.of("Europe/Vilnius");
        return ZonedDateTime.now(zoneId);
    }
}
