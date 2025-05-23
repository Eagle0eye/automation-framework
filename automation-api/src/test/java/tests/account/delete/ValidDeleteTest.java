package tests.account.delete;

import DTO.Login;
import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import provider.AuthProvider;
import utils.AllureUtils;

import static expectaions.api.ExpectedResponses.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

@Epic("ACCOUNT")
@Feature("DELETE OPERATION")
public class ValidDeleteTest extends BaseAPIClient {
    @Story("https://automationexercise.com/api/deleteAccount")
    @Description("Delete an account")
    @Severity(SeverityLevel.CRITICAL)
    @Test( dataProvider = "deletedAccount",
            dataProviderClass = AuthProvider.class
            , groups = {"API"})
    public void deleteAccount(Login form){

        AllureUtils.attachJsonSchema("schemas/login-response-schema.json", "Login Response Schema");
        Response response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.MULTIPART)
                .multiPart("email", form.getEmail())
                .multiPart("password", form.getPassword())
                .when().delete("/deleteAccount")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/login-response-schema.json"))
                .statusCode(200)
                .extract()
                .response();
        assertEquals(response.getBody().jsonPath().getInt("responseCode"), OK);
        assertEquals(response.getBody().jsonPath().get("message"), DELETED_ACCOUNT);
    }
}
