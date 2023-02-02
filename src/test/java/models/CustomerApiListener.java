package models;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomerApiListener {

    private static AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withButifuleAllure(){
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setRequestTemplate("responce.ftl");
        return FILTER;
    }

}
