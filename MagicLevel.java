package a11847898;

/**
 * Defines the various magic levels.
 * wizards have a magic level.
 * in order to be able to use specific spells a minimum magic level is necessary.
 * Note: the compiler generated default constructor may not be sufficient for your implementation
 */
public enum MagicLevel {
    NOOB(50, "*"),
    ADEPT(100, "**"),
    STUDENT(200, "***"),
    EXPERT(500, "****"),
    MASTER(1000, "*****");

    private final int basicMP;
    private final String asterisk;

    MagicLevel(int basicMP, String asterisk) {
        this.basicMP = basicMP;
        this.asterisk = asterisk;
    }

    public int toMana() {
        return basicMP;
    }

    @Override
    public String toString() {
        return asterisk;
    }
}