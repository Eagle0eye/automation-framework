package tests.brand;

import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static expectaions.api.ExpectedResponses.NOT_SUPPORTED;
import static expectaions.api.ExpectedResponses.NOT_SUPPORTED_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static utils.AllureUtils.attachJsonSchema;

@Epic("BRANDS")
@Feature("UPDATE BRANDS")
@Story("PUT https://automationexercise.com/api/brandsList")
public class UpdateBrands extends BaseAPIClient {


    @Description("update Brands not allowed")
    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"API"})
    public void updateBrandsNotAllowed() {
        attachJsonSchema("/schemas/login-response-schema.json","Response Schema");
        Response response = given().when().put("/brandsList").then()
                .body(matchesJsonSchemaInClasspath("schemas/login-response-schema.json")).extract().response();
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("responseCode"), NOT_SUPPORTED);
        assertEquals(jsonPath.getString("message"), NOT_SUPPORTED_MESSAGE);

    }


}
