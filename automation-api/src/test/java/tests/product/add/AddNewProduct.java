package tests.product;

import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static expectaions.api.ExpectedResponses.NOT_SUPPORTED;
import static expectaions.api.ExpectedResponses.NOT_SUPPORTED_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static utils.AllureUtils.attachJsonSchema;

@Epic("PRODUCTS")
@Feature("POST https://automationexercise.com/api/productsList")
public class AddNewProduct extends BaseAPIClient {
    @Description("Add a new product is not allowed")
    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"API"})
    public void addNewProductNotAllowed() {
        attachJsonSchema("/schemas/login-response-schema.json","Response Schema");
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .post("/productsList")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/login-response-schema.json")).extract().response();
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("responseCode"), NOT_SUPPORTED);
        assertEquals(jsonPath.getString("message"), NOT_SUPPORTED_MESSAGE);

    }
}
