package test.utils;

import test.models.login.LoginRequest;

public class TestDataBuilder {

    public static LoginRequest getDefaultLoginRequest() {
        return new LoginRequest(
                ConfigReader.getUsername(),
                ConfigReader.getPassword()
        );
    }
}