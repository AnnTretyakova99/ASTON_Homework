import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanTestng {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        given()
                .queryParam("type", "testng")
                .queryParam("priority", "high")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.type", equalTo("testng"))
                .body("args.priority", equalTo("high"));
    }

    @Test
    public void testPostRawText() {
        String textMessage = "Hello from TestNg";
        given()
                .contentType(ContentType.TEXT)
                .body(textMessage)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(textMessage));
    }

    @Test
    public void testPostFormData() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("framework", "testng")
                .formParam("version", "7.10.2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("from.framework", equalTo("testng"))
                .body("from.version", equalTo("7.10.2"));
    }

    @Test
    public void testPutRequest() {
        String jsonBody = "{\"status\": \"updated\", \"code\": 200}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.status", equalTo("updated"))
                .body("json.code", equalTo(200));
    }
}
