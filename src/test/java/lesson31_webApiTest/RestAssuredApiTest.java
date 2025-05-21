package lesson31_webApiTest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class RestAssuredApiTest {

    @Test
    public void testGetCarBrandsWithRestAssured() {
        RestAssured.baseURI = "https://qauto.forstudy.space";

        given()
                .when()
                .get("/api/cars/brands")
                .then()
                .statusCode(200)
                .body("data.find { it.id == 1 }.title", equalTo("Audi"));
    }
}
