package io.github.tkaczenko.Authentication.src.security.encryption;

/**
 * Created by tkaczenko on 10.10.16.
 */
public abstract class Alphabet {
    public abstract char[] getAlphabet();

    public boolean isPresent(char c) {
        c = Character.toUpperCase(c);
        char[] alphabet = getAlphabet();
        for (char alphabetChar :
                alphabet) {
            if (alphabetChar == c) {
                return true;
            }
        }
        return false;
    }

    public static class Ukrainian extends Alphabet {
        private static final char[] ALPHABET = {
                'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Є', 'Ж', 'З', 'И',
                'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
                'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь',
                'Ю', 'Я', '’', '_', '.'
        };

        public Ukrainian() {

        }

        @Override
        public char[] getAlphabet() {
            return ALPHABET;
        }
    }
}
