package tests.account;


import DTO.Login;
import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import provider.AuthProvider;
import utils.AllureUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("ACCOUNT")
@Feature("DELETE OPERATION")
public class InvalidDeleteTest extends BaseAPIClient {
    @Story("Invalid Delete an account with /verifyLogin")
    @Severity(SeverityLevel.CRITICAL)
    @Test( dataProvider = "deletedAccount",
            dataProviderClass = AuthProvider.class
            , groups = {"API"})
    public void LoginByValidCredentials(Login form){

        AllureUtils.attachJsonSchema("schemas/login-response-schema.json", "Login Response Schema");
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.MULTIPART)
                .multiPart("email", form.getEmail())
                .multiPart("password", form.getPassword())
                .when().delete("/verifyLogin")
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
