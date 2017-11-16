package enumerations;

/**
 * Enumeration for grade of the discipline
 */
public enum Grade {
    A(5) {
        @Override
        public String gradeEU() {
            return "A";
        }
    },
    B(4) {
        @Override
        public String gradeEU() {
            return "B";
        }
    },
    C(3) {
        @Override
        public String gradeEU() {
            return "C";
        }
    },
    D(2) {
        @Override
        public String gradeEU() {
            return "D";
        }
    },
    E(1) {
        @Override
        public String gradeEU() {
            return "E";
        }
    };

    private int value;

    public static Grade get(int index) {
        switch (index) {
            case 1:
                return E;
            case 2:
                return D;
            case 3:
                return C;
            case 4:
                return B;
            case 5:
                return A;
            default:
                return E;
        }
    }

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Get Europe name of the grade
     *
     * @return {@code String} of the grade
     */
    public abstract String gradeEU();
}
