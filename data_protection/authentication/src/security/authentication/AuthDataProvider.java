package io.github.tkaczenko.Authentication.src.security.authentication;

/**
 * Created by tkaczenko on 11.10.16.
 */
public interface AuthDataProvider {
    String getAuthKey(String login);
}
