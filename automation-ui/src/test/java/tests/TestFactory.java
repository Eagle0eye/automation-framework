package tests;


import base.DRIVERS;
import org.testng.annotations.Factory;
import tests.contactus.TC06_ContactUs_Test;
import tests.login.TC02_Login_ValidTest;
import tests.login.TC03_Login_InvalidTest;
import tests.login.TC04_Logout_Test;
import tests.register.TC01_Registration_ValidTest;
import tests.register.TC05_Registration_InvalidTest;
import tests.testcases.TC07_TestCases_Test;


public class TestFactory {

    @Factory
    public Object[] createInstances() {
        DRIVERS driver = DRIVERS.CHROME;
        return new Object[] {

                new TC01_Registration_ValidTest(),
                new TC02_Login_ValidTest(),
                new TC03_Login_InvalidTest(),
                new TC04_Logout_Test(),
                new TC05_Registration_InvalidTest(),
                new TC06_ContactUs_Test(),
                new TC07_TestCases_Test(),

        };
    }

}