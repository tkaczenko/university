package enumerations;

/**
 * Enumeration for gender
 */
public enum Sex {
    MALE {
        @Override
        public String sex() {
            return "male";
        }
    },
    FEMALE {
        @Override
        public String sex() {
            return "female";
        }
    };

    public static Sex get(int index) {
        switch (index) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            default:
                return MALE;
        }
    }

    /**
     * Get name of the gender
     *
     * @return {@code String} of the gender name
     */
    public abstract String sex();
}
