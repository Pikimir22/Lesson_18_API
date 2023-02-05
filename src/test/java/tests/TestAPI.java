package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static models.CustomerApiListener.withButifuleAllure;
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



        String person = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

//        bodyModel.setName("name");
//        bodyModel.setMorpheus("morpheus");
//        bodyModel.setJob("job");
//        bodyModel.setLeader("leader");

        given()
                .log().all()
                .body(person)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name",is("morpheus"))
                .body("job",is("leader"));


    }

    @Test
    @Description("Registrations Success")
    void post_200_registrations() {

        String regist = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";

        LoginResponseModel responseModel = given(loginRequest)
                .body(regist)
                .when()
                .post("/login")
                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseModel.class);

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
