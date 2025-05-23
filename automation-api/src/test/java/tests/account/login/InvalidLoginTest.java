package tests.account.login;


import DTO.Login;
import base.BaseAPIClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import provider.AuthProvider;
import utils.AllureUtils;


import static io.restassured.RestAssured.given;

@Epic("ACCOUNT")
@Feature("LOGIN")
@Story("POST https://automationexercise.com/api/verifyLogin")
public class InvalidLoginTest extends BaseAPIClient {

    private static final Logger log = LoggerFactory.getLogger(InvalidLoginTest.class);

    @Description("Invalid login with incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "loginInvalidIncorrectPassword",
            dataProviderClass = AuthProvider.class
            , groups = {"API"}
    )
    public void LoginByValidCredentials(Login form){
        Response response = this.FunctionTest(form);
        Assert.assertEquals(response.getBody().jsonPath().getInt("responseCode"),
                form.getExpectation().getStatusCode());
        Assert.assertEquals(response.getBody().jsonPath().getString("message"),
                form.getExpectation().getMessage());    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Invalid Login with Empty Email")
    @Test(dataProvider = "loginInvalidEmptyEmail",dataProviderClass = AuthProvider.class
            , groups = {"API"})
    public void LoginByEmptyEmail(Login form){
        Response response = this.FunctionTest(form);
        Assert.assertNull(response);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Invalid Login with not found email")
    @Test(dataProvider = "loginInvalidNotFoundEmail",dataProviderClass = AuthProvider.class
            , groups = {"API"})
    public void LoginByNotFoundEmail(Login form){
        Response response = this.FunctionTest(form);
        Assert.assertEquals(response.getBody().jsonPath().getInt("responseCode"), form.getExpectation().getStatusCode());
        Assert.assertEquals(response.getBody().jsonPath().getString("message"), form.getExpectation().getMessage());
    }






    private Response FunctionTest(Login form){
        try {
            AllureUtils.attachJsonSchema("schemas/login-response-schema.json", "Login Response Schema");

            return   given()
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.MULTIPART)
                    .multiPart("email", form.getEmail())
                    .multiPart("password", form.getPassword())
                    .when().post("/verifyLogin")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();
        }
        catch (Exception e){
            log.error("Error: {}",e.getMessage());
            return  null;
        }

    }
}
