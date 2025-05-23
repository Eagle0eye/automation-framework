package tests;

import DTO.Login;
import DTO.Register;
import provider.RegisterProvider;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.slf4j.Slf4j;
import mongo.AuthRepository;
import mongo.RegisterRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;



import io.qameta.allure.*;

@Slf4j
@Epic("User Registration Module")
@Feature("Register and Manage Users")
@Listeners({AllureTestNg.class})
public class RegisterNewUserTest  {
    private  RegisterRepository registerRepo;
    private  AuthRepository authRepo;
    private String deleted_email;
    @BeforeTest
    public void beforeTest() {
        registerRepo = new RegisterRepository();
        authRepo = new AuthRepository();
    }

    @Test(priority = 1, dataProvider = "RegisterValidFullInfo", dataProviderClass = RegisterProvider.class,
            description = "Register new account")
    @Story("Register a new user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Register a user with full valid information and verify user is created")
    public void addNewUser(Register register) {
        registerRepo.insertUser(register);
        deleted_email = register.getEmail();
        boolean value = authRepo.userExists(deleted_email);
        Assert.assertTrue(value, "User should exist after registration");
    }

    @Test(priority = 2, description = "Get All Valid Credentials")
    @Story("Retrieve credentials")
    @Severity(SeverityLevel.NORMAL)
    public void getAllValidCredentials() {
        List<Login> users = authRepo.getAllUsers();
        for (Login user : users) {
            log.info("User: {}", user);
        }
    }

    @Test(priority = 3, description = "Search about existing user by email")
    @Story("Search for existing user")
    @Severity(SeverityLevel.CRITICAL)
    public void searchUserByEmail() {
        Assert.assertTrue(authRepo.userExists(deleted_email));
    }

    @Test(priority = 4, description = "Delete user")
    @Story("Delete user")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteUser() {
        registerRepo.deleteUser(deleted_email);
    }

    @Test(priority = 5, description = "Search for a user that should not be found")
    @Story("Search for deleted user")
    @Severity(SeverityLevel.NORMAL)
    public void searchUserByEmailNotFound() {
        Assert.assertFalse(authRepo.userExists(deleted_email));
    }
}
