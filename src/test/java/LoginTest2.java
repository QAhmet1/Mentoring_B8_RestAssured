import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;

public class LoginTest2 {


    @Test
    void loginTest2(){
        baseURI="https://test.mersys.io";
        String username = "turkeyts";
        String password = "TechnoStudy123";
        String rememberMe = "true";

        Map<String,String> credentials=new HashMap<>();
        credentials.put("username",username);
        credentials.put("password",password);
        credentials.put("rememberMe",rememberMe);

        given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .log().body();
    }
}
