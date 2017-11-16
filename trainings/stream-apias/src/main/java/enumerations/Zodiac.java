package enumerations;

/**
 * Enumeration for zodiac
 */
public enum Zodiac {
    ARIES(1) {
        @Override
        public String zodiac() {
            return "Aries";
        }
    },
    TAURUS(2) {
        @Override
        public String zodiac() {
            return "Taurus";
        }
    },
    GEMINI(3) {
        @Override
        public String zodiac() {
            return "Gemini";
        }
    },
    CANCER(4) {
        @Override
        public String zodiac() {
            return "Cancer";
        }
    },
    LEO(5) {
        @Override
        public String zodiac() {
            return "Leo";
        }
    },
    VIRGO(6) {
        @Override
        public String zodiac() {
            return "Virgio";
        }
    },
    LIBRA(7) {
        @Override
        public String zodiac() {
            return "Libra";
        }
    },
    SCORPIO(8) {
        @Override
        public String zodiac() {
            return "Scorpio";
        }
    },
    SAGGITTARIUS(9) {
        @Override
        public String zodiac() {
            return "Saggittarius";
        }
    },
    CAPRICORN(10) {
        @Override
        public String zodiac() {
            return "Capricorn";
        }
    },
    AQUARIUS(11) {
        @Override
        public String zodiac() {
            return "Aquarius";
        }

    },
    PISCES(12) {
        @Override
        public String zodiac() {
            return "Pisces";
        }
    };

    private int value;

    public static Zodiac get(int index) {
        switch (index) {
            case 1:
                return ARIES;
            case 2:
                return TAURUS;
            case 3:
                return GEMINI;
            case 4:
                return CANCER;
            case 5:
                return LEO;
            case 6:
                return VIRGO;
            case 7:
                return LIBRA;
            case 8:
                return SCORPIO;
            case 9:
                return SAGGITTARIUS;
            case 10:
                return CAPRICORN;
            case 11:
                return AQUARIUS;
            case 12:
                return PISCES;
            default:
                return ARIES;
        }
    }

    Zodiac(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Get name of the zodiac
     *
     * @return {@code String} of the zodiac name
     */
    public abstract String zodiac();
}
