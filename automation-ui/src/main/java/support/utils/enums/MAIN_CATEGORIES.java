package support.utils.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MAIN_CATEGORIES {
    WOMEN("Women"), MEN("Men"), KIDS("Kids");
    private final String mainCategory;
    @Override
    public String toString() {
        return mainCategory;
    }
}
