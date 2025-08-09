package support.utils.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BRAND {
    POLO("Polo"),
    H_M("H&M"),
    MADAME("Madame"),
    MAST_HARBOUR("Mast & Harbour"),
    BABYHUG("Babyhug"),
    ALLEN_SOLLY_JUNIOR("Allen Solly Junior"),
    KOOKIE_KIDS("Kookie Kids"),
    BIBA("Biba");

    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }
}
