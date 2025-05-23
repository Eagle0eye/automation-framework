package tests.account;


import DTO.Login;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import provider.AuthProvider;
import base.BaseAPIClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@Epic("ACCOUNT")
@Feature("LOGIN - SUCCESSFUL")
public class ValidLoginTest extends BaseAPIClient {



    @Description("Login By valid Credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Login By valid Credentials", dataProvider = "loginValidCredentials",
            dataProviderClass = AuthProvider.class
            , groups = {"API"})
    public void LoginByValidCredentials(Login form){

        AllureUtils.attachJsonSchema("schemas/login-response-schema.json", "Login Response Schema");
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.MULTIPART)
                .multiPart("email", form.getEmail())
                .multiPart("password", form.getPassword())
                .when().post("/verifyLogin")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/login-response-schema.json"))
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(response.getBody().jsonPath().getInt("responseCode"), form.getExpectation().getStatusCode());
        Assert.assertEquals(response.getBody().jsonPath().get("message"), form.getExpectation().getMessage());
    }
}
