package tests.account.view;

import DTO.Login;
import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import provider.AuthProvider;
import utils.AllureUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@Epic("ACCOUNT")
@Feature("GET USER INFORMATION")
public class GetAccountInfoTest extends BaseAPIClient {

    @Story("Get user information by email")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "loginValidCredentials",
            dataProviderClass = AuthProvider.class
            , groups = {"API"}
    )
    public void getUserInfo(Login login) {
        AllureUtils.attachJsonSchema("schemas/userinfo-schema.json", "User Details Response Schema");


        Response response = given()

                .param("email",login.getEmail())
                .param("password",login.getPassword())
                .when().get("/getUserDetailByEmail")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/account-details-scheme.json"))
                .extract()
                .response();

        Assert.assertEquals(response.getBody().jsonPath().getInt("responseCode"),login.getExpectation().getStatusCode());

    }

}
