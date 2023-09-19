import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {

    @Test
    void login(){
        given()
                .body("{\n" +
                        "  \"username\": \"turkeyts\",\n" +
                        "  \"password\": \"TechnoStudy123\",\n" +
                        "  \"rememberMe\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("https://test.mersys.io/auth/login")
                .then()
                .statusCode(200)
                .log().body()
        ;

    }
}
