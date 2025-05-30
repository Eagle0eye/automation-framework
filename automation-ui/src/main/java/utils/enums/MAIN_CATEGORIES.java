package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MAIN_CATEGORIES {
    WOMEN("Women"), MEN("Men"), KIDS("Kids");
    private final String mainCategory;
    @Override
    public String toString() {
        return mainCategory;
    }
}
