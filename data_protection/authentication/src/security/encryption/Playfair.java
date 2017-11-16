package io.github.tkaczenko.Authentication.src.security.encryption;

/**
 * Created by tkaczenko on 10.10.16.
 */
public class Playfair {
    private static final int DEFAULT_ROW_SIZE = 7;
    private static final int DEFAULT_COL_SIZE = 5;

    private final Alphabet alphabet;
    private char keyMatrix[][] = new char[DEFAULT_ROW_SIZE][DEFAULT_COL_SIZE];
    private Character delimetr;

    public Playfair() {
        this.alphabet = new Alphabet.Ukrainian();
    }

    public Playfair(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, String key) {
        insertKey(key);

        String cipherText = "";
        text = text.replaceAll(" ", "");
        text = text.toUpperCase();
        int len = text.length();

        if (len % 2 != 0) {
            text += '’';
            ++len;
        }

        for (int i = 0; i < len - 1; i += 2) {
            cipherText += encryptBigram(text.substring(i, i + 2));
        }
        return cipherText.toLowerCase();
    }

    public String decrypt(String cipherText, String key) {
        String decryptText = "";
        cipherText = cipherText.replaceAll(" ", "");
        cipherText = cipherText.toUpperCase();
        int len = cipherText.length();
        for(int i = 0; i < len-1; i += 2) {
            decryptText += decryptBigram(cipherText.substring(i, i + 2));
        }
        return decryptText.toLowerCase();
    }

    private void insertKey(String key) {
        key = key.toUpperCase();

        int a = 0, b = 0;
        for (int i = 0; i < key.length(); i++) {
            if (!isRepeated(key.charAt(i))) {
                keyMatrix[a][b++] = key.charAt(i);
                if (b > 4) {
                    b = 0;
                    a++;
                }
            }
        }

        char[] alp = alphabet.getAlphabet();
        char p = alp[0];
        int count = 0;
        while (a < keyMatrix.length) {
            while (b < keyMatrix[a].length) {
                if (!isRepeated(p)) {
                    keyMatrix[a][b++] = p;
                }
                count++;
                if (count < keyMatrix.length * keyMatrix[a].length) {
                    p = alp[count];
                }
            }
            b = 0;
            a++;
        }
    }

    private String encryptBigram(String bigram) {
        bigram = bigram.toUpperCase();
        String cipherChar = "";

        char a = bigram.charAt(0), b = bigram.charAt(1);

        if (a == b) {
            b = delimetr;
            cipherChar += a;
            cipherChar += b;
            return cipherChar;
        }

        int r1, c1, r2, c2;
        r1 = rowPos(a);
        c1 = columnPos(a);
        r2 = rowPos(b);
        c2 = columnPos(b);

        if (c1 == c2) {
            ++r1;
            ++r2;
            if (r1 >= keyMatrix.length) {
                r1 = 0;
            }
            if (r2 >= keyMatrix.length) {
                r2 = 0;
            }
            cipherChar += keyMatrix[r1][c1];
            cipherChar += keyMatrix[r2][c2];
        } else if (r1 == r2) {
            ++c1;
            ++c2;
            if (c1 > keyMatrix[0].length) {
                c1 = 0;
            }
            if (c2 > keyMatrix[0].length) {
                c2 = 0;
            }
            cipherChar += keyMatrix[r1][c1];
            cipherChar += keyMatrix[r2][c2];
        } else {
            cipherChar += keyMatrix[r1][c2];
            cipherChar += keyMatrix[r2][c1];
        }
        return cipherChar;
    }

    private String decryptBigram(String bigram) {
        bigram = bigram.toUpperCase();
        String decryptBigram = "";

        char a = bigram.charAt(0), b = bigram.charAt(1);


        if (b == delimetr) {
            decryptBigram += a;
            decryptBigram += a;
            return decryptBigram;
        }

        int r1, c1, r2, c2;
        r1 = rowPos(a);
        c1 = columnPos(a);
        r2 = rowPos(b);
        c2 = columnPos(b);

        if (c1 == c2) {
            --r1;
            --r2;
            if (r1 < 0) {
                r1 = keyMatrix.length - 1;
            }
            if (r2 < 0) {
                r2 = keyMatrix.length - 1;
            }
            decryptBigram += keyMatrix[r1][c2];
            decryptBigram += keyMatrix[r2][c1];
        }
        else if (r1 == r2) {
            --c1;
            --c2;
            if (c1 < 0) {
                c1 = keyMatrix[0].length - 1;
            }
            if (c2 < 0) {
                c2 = keyMatrix[0].length - 1;
            }
            decryptBigram += keyMatrix[r1][c1];
            decryptBigram += keyMatrix[r2][c2];
        } else {
            decryptBigram += keyMatrix[r1][c2];
            decryptBigram += keyMatrix[r2][c1];
        }
        return decryptBigram;
    }

    private boolean isRepeated(char c) {
        if (!alphabet.isPresent(c)) {
            return false;
        }
        for (char[] arr :
                keyMatrix) {
            for (char character :
                    arr) {
                if (character == c) {
                    return true;
                }
            }
        }
        return false;
    }

    private int rowPos(char c) {
        for(int i = 0; i < keyMatrix.length; i++) {
            for(int j = 0; j < keyMatrix[i].length; j++) {
                if (keyMatrix[i][j] == c)
                    return i;
            }
        }
        return -1;
    }

    private int columnPos(char c) {
        for(int i = 0; i < keyMatrix.length; i++) {
            for(int j=0;j < keyMatrix[i].length;j++) {
                if (keyMatrix[i][j] == c)
                    return j;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String separator = ", ";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < keyMatrix.length; i++) {
            for(int j = 0; j < keyMatrix[i].length; j++){
                result.append(keyMatrix[i][j]);
                result.append(separator);
            }
            result.setLength(result.length() - separator.length());
            result.append("\n");
        }
        return result.toString();
    }

    public Character getDelimetr() {
        return delimetr;
    }

    public void setDelimetr(Character delimetr) {
        this.delimetr = delimetr;
    }
}
