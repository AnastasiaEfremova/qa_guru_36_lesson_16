package test.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;
import static test.helpers.CustomAllureListener.withCustomTemplates;

public class BaseSpecification {

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .setContentType(JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification status200ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification status201ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification status204ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.ALL)
            .build();
}