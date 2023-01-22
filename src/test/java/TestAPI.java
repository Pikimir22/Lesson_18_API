
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TestAPI {

    @BeforeAll
    static void baseUrl(){
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test
    @Description("Create users")
    void post_201_status() {

        String users = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(users)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"));

    }

    @Test
    @Description("Registrations Success")
    void post_200_registrations() {

        String regist = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(regist)
                .when()
                .post("/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }


    @Test
    @Description("List Users")
    void get_200_status() {
        given()
                .log().all()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2));
    }


    @Test
    @Description("Register Unsuccess")
    void post_400_status() {

String param = "{\"email\": \"sydney@fife\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(param)
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


    @Test
    @Description("Delete Response")
    void get_200_status_response() {
        given()
                .log().all()
                .when()
                .get("/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("total_pages", is(2));
    }


}
