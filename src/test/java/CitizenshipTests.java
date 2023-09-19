import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CitizenshipTests {

    Citizenship user=new Citizenship();
    RequestSpecification rec;
    Cookies cookies;
    String citizenName;
    @BeforeClass
    void login(){
        baseURI="https://test.mersys.io";
        String username = "turkeyts";
        String password = "TechnoStudy123";
        String rememberMe = "true";

        rec=given().log().body().contentType(ContentType.JSON);

        Map<String,String> credentials=new HashMap<>();
        credentials.put("username",username);
        credentials.put("password",password);
        credentials.put("rememberMe",rememberMe);

       cookies=  given()
               .spec(rec)
                .body(credentials)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
               .extract().detailedCookies();
        System.out.println("cookies = " + cookies);
    }
    @Test
    void createCitizenship(){
      citizenName= RandomStringUtils.randomAlphabetic(6);

        user.setName(citizenName);
        user.setShortName("test");
        user.setId( given()
                .body(user)
                .contentType(ContentType.JSON)
                .cookies(cookies)
                .when()
                .post("/school-service/api/citizenships")
                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getString("id"));

        System.out.println("user.getId() = " + user.getId());

     }
    @Test(dependsOnMethods ="createCitizenship")
    void createCitizenshipNegative(){
        user.setName(citizenName);
        user.setShortName("test");
        given()
                .body(user)
                .contentType(ContentType.JSON)
                .cookies(cookies)
                .when()
                .post("/school-service/api/citizenships")
                .then()
                .log().body()
                .statusCode(400)
        ;


    }

    @Test(dependsOnMethods ="createCitizenship" )
    void getCitizenById(){

        given()

                .spec(rec)
                .cookies(cookies)
                .when()
                .get("/school-service/api/citizenships/"+user.getId())
                .then()
                .log().body()
                .statusCode(200)
        ;


    }
    }

