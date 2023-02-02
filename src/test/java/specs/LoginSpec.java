package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static models.CustomerApiListener.withButifuleAllure;
import static org.hamcrest.Matchers.notNullValue;

public class LoginSpec {

    public static RequestSpecification loginRequest = with()
            .log().uri()
            .log().headers()
            .log().body()
            .filter(withButifuleAllure())
            .contentType(ContentType.JSON)
            .basePath("/api");


    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("token",notNullValue())
            .build();
}
