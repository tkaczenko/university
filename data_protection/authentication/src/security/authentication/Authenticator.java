package io.github.tkaczenko.Authentication.src.security.authentication;

import io.github.tkaczenko.Authentication.src.security.encryption.Playfair;

/**
 * Created by tkaczenko on 11.10.16.
 */
public class Authenticator {
    private AuthDataProvider dataProvider;
    private Playfair playfair;

    public void setDataProvider(AuthDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setEncryptionSystem(Playfair playfair) {
        this.playfair = playfair;
    }

    public AuthResult authenticate(String username, String password) {
        if (playfair == null) {
            return AuthResult.NO_ENCRYPTION_SYSTEM;
        }
        if (username == null || dataProvider == null || password == null) {
            return AuthResult.NO_DATA;
        }
        String authKey = dataProvider.getAuthKey(username);
        if (authKey == null) {
            return AuthResult.NO_SUCH_USER;
        }
        if (!playfair.encrypt(password, username).contentEquals(authKey)) {
            return AuthResult.WRONG_PASSWORD;
        }
        return AuthResult.OK;
    }

    public enum AuthResult {
        OK,
        NO_SUCH_USER,
        WRONG_PASSWORD,
        NO_DATA,
        NO_ENCRYPTION_SYSTEM
    }
}
