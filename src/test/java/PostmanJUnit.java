import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PostmanJUnit {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    void testGetJunit() {
        given().
                queryParam("text", "hello")
                .queryParam("id", "121")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.text", equalTo("hello"))
                .body("args.id", equalTo("121"));
    }

    @Test
    void testPostRawtext() {
        String myText = "Simple raw text message";
        given()
                .contentType(ContentType.TEXT)
                .body(myText)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(myText));
    }

    @Test
    void testPostFormData() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("username", "admin")
                .formParam("status", "active")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("from.username", equalTo("admin"))
                .body("from.status", equalTo("active"));
    }

    @Test
    void testPutRequest() {
        String jsonBody = "{\"update\" : \"success\"}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.update", equalTo("success"));
    }
}