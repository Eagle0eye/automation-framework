package tests.brand;

import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.AllureUtils;

import java.util.List;
import java.util.Map;

import static expectaions.api.ExpectedResponses.OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

@Epic("BRANDS")
@Feature("VIEW BRANDS")
@Story("GET https://automationexercise.com/api/brandsList")
public class GetAllBrands extends BaseAPIClient {

    @Description("view all brands")
    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"API"})
    public void getAllBrands(){
        AllureUtils.attachJsonSchema("schemas/brands-schema.json.json","Brands Response Schema");

        Response response = given().when().get("/brandsList").then()
                .body(matchesJsonSchemaInClasspath("schemas/brands-schema.json"))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("responseCode"), OK);
        List<Map> brands = jsonPath.getList("brands", Map.class);
        assertEquals(brands.size(), 34);

    }
}
