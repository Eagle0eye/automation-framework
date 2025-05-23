package tests.product;

import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import provider.ProductProvider;

import java.util.List;
import java.util.Map;

import static expectaions.api.ExpectedResponses.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static utils.AllureUtils.attachJsonSchema;

@Epic("PRODUCTS")
@Feature("POST https://automationexercise.com/api/searchProduct")
public class ValidSearchProductTest extends BaseAPIClient {
    @Description("valid search about products using parameter search_product")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "SearchValues", dataProviderClass = ProductProvider.class,groups = {"API"})
    public void validSearchProduct(String search_product,int amount) {
        attachJsonSchema("/schemas/products-schema.json","Response Schema");
        Response response = given()
                .contentType(ContentType.MULTIPART)
                .multiPart("search_product", search_product)
                .when()
                .post("/searchProduct")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/products-schema.json")).extract().response();
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("responseCode"), OK);
        assertEquals(jsonPath.getList("products").size(),amount);
    }
}
