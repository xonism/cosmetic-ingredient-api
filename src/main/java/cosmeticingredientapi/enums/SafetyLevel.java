package cosmeticingredientapi.enums;

import lombok.Getter;

@Getter
public enum SafetyLevel {

    SAFE("safe"),
    MEDIUM("medium"),
    UNSAFE("unsafe"),
    UNCATEGORIZED("uncategorized");

    private final String name;

    SafetyLevel (String name) {
        this.name = name;
    }
}
