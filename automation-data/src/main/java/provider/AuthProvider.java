package provider;

import DTO.Login;
import expectaions.Expectation;
import org.testng.annotations.DataProvider;

import static expectaions.api.ExpectedResponses.*;
import static variables.AuthVariables.*;

public class AuthProvider {



    @DataProvider(name = "deletedAccount")
    public Login[] deleteAccount() {
        return new Login[]{buildLogin(validEmail,validPassword,NOT_SUPPORTED,NOT_SUPPORTED_MESSAGE)};
    }
    @DataProvider(name = "loginValidCredentials")
    public static Login[] loginValidData() {
        return new Login[]{buildLogin(validEmail, validPassword, OK, USER_EXISTS)};
    }

    @DataProvider(name = "loginInvalidIncorrectPassword")
    public static Login[] loginInvalidIncorrectPassword() {
        return new Login[]{buildLogin(validEmail, invalidPassword, NOT_FOUND, USER_NOT_FOUND)};
    }

    @DataProvider(name = "loginInvalidEmptyEmail")
    public static Login[] loginInvalidEmptyEmail() {
        return new Login[]{buildLogin(null, validPassword, BAD_REQUEST, MISSING_CREDENTIALS)};
    }

    @DataProvider(name = "loginInvalidNotFoundEmail")
    public static Login[] loginInvalidNotFoundEmail() {
        return new Login[]{buildLogin(invalidEmail, invalidPassword, NOT_FOUND, USER_NOT_FOUND)};
    }

    private static Login buildLogin(String email, String password, int statusCode, String message) {
        return Login.builder()
                .email(email)
                .password(password)
                .expectation(Expectation.builder()
                        .statusCode(statusCode)
                        .message(message)
                        .build())
                .build();
    }
}
