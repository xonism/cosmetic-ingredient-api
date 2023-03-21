package cosmeticingredientapi.utils;

import org.springframework.data.domain.Sort;

public class SortUtils {
    public static final Sort SORT_ID_ASC = Sort.by(Sort.Direction.ASC, "id");

    private SortUtils() {

    }
}
