package tests.testcases;

import base.BaseUITest;
import base.DRIVERS;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.testcases.TestCasePage;

import static org.testng.Assert.assertEquals;

public class TC07_TestCases_Test extends BaseUITest {

    public TC07_TestCases_Test() {
        super(DRIVERS.CHROME);
    }
    @Test
    public void verifyTestCasesPage() {
        HomePage homePage = new HomePage(driver);
        TestCasePage page = homePage.navigateTo().TestCasesPage();
        String expected_message = "TEST CASES";
        assertEquals(page.getTitle(),expected_message);
    }




}
