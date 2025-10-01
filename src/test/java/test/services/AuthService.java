package test.services;

import test.models.login.LoginRequest;
import test.models.login.LoginResponse;
import test.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static test.BaseTest.config;
import static test.api.ApiLoginClient.login;

public class AuthService {

    public static LoginResponse loginWithDefaultUser() {
        LoginRequest loginRequest = new LoginRequest(
                config.username(),
                config.password()
        );
        return login(loginRequest);
    }

    public static void verifyLoginResponse(LoginResponse loginResponse) {
        assertNotNull(loginResponse.getToken(), "Token should not be null");
        assertEquals(config.username(), loginResponse.getUsername(),
                "Username should match");
    }
}