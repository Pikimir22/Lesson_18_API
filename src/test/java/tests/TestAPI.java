package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import models.LoginRequestModel;
import models.UserRequestCreate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.LoginSpec.loginRequest;
import static specs.LoginSpec.loginResponseSpec;

public class TestAPI {

    @BeforeAll
    static void baseUrl() {
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test
    @Description("Create users")
    void post_201_status() {


        UserRequestCreate userCreate = new UserRequestCreate();

        userCreate.setName("morpheus");
        userCreate.setJob("leader");

        given()
                .log().all()
                .body(userCreate)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));


    }

    @Test
    @Description("Registrations Success")
    void post_200_registrations() {

        LoginRequestModel requestModel = new LoginRequestModel();
        requestModel.setEmail("eve.holt@reqres.in");
        requestModel.setPassword("pistol");


        LoginRequestModel responseModel = given(loginRequest)
                .body(requestModel)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginRequestModel.class);

        assertThat(responseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

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

        LoginRequestModel requestModel = new LoginRequestModel();

        requestModel.setEmail("email");
        requestModel.setEmail("sydney@fife");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestModel)
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
