package cosmeticingredientapi.enums;

public enum SafetyLevel {
    SAFE("safe"),
    MEDIUM("medium"),
    UNSAFE("unsafe"),
    UNDEFINED("undefined");

    private final String value;

    SafetyLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
