package test.api;

import test.models.login.LoginRequest;
import test.models.login.LoginResponse;

import static io.restassured.RestAssured.given;
import static test.specs.BaseSpecification.requestSpecification;
import static test.specs.BaseSpecification.status200ResponseSpec;

public class ApiLoginClient {

    public static LoginResponse login(LoginRequest loginRequest) {
        return given(requestSpecification)
                .basePath("/Account/v1/Login")
                .body(loginRequest)
                .when()
                .post()
                .then()
                .spec(status200ResponseSpec)
                .extract().as(LoginResponse.class);
    }
}
