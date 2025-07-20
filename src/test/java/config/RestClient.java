package config;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    public static RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .filter(new AllureRestAssured())
                .log().all();
    }
}