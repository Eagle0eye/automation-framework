package utils.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BRAND {
    POLO("POLO"),
    H_M("H&M"),
    MADAME("MADAME"),
    MAST_HARBOUR("MAST & HARBOUR"),
    BABYHUG("BABYHUG"),
    ALLEN_SOLLY_JUNIOR("ALLEN SOLLY JUNIOR"),
    KOOKIE_KIDS("KOOKIE KIDS"),
    BIBA("BIBA");

    private final String displayName;

    @Override
    public String toString() {
        return displayName.toUpperCase();
    }
}
