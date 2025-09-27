package test.services;

import test.models.login.LoginRequest;
import test.models.login.LoginResponse;
import test.utils.ConfigReader;
import test.utils.TestDataBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static test.api.ApiLoginClient.login;

public class AuthService {
    
    public static LoginResponse loginWithDefaultUser() {
        LoginRequest loginRequest = TestDataBuilder.getDefaultLoginRequest();
        return login(loginRequest);
    }
    
    public static void verifyLoginResponse(LoginResponse loginResponse) {
        assertNotNull(loginResponse.getToken(), "Token should not be null");
        assertEquals(ConfigReader.getUsername(), loginResponse.getUsername(), 
                    "Username should match");
    }
}