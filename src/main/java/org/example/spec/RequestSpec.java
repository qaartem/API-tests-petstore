package org.example.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {
    private static RequestSpecBuilder baseSpecBuilder(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setAccept(ContentType.JSON);
        return requestSpecBuilder;
    }
}
