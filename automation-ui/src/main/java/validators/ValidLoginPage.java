    package validation_pages;


    import components.BasePage;
    import components.Header;
    import models.UserInfo;
    import mongo.AuthRepository;
    import org.bson.Document;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;

    import services.UserProfileServiceImpl;

    import static mapper.UserMapper.mapDocumentToUser;


    public class ValidLoginPage extends BasePage {

        private final String email;
        private final AuthRepository repository;
        private final UserProfileServiceImpl userService;

        public ValidLoginPage(WebDriver driver,String email) {
            super(driver);
            repository = new AuthRepository();
            userService = new UserProfileServiceImpl();
            this.email = email;
        }

        public Header correctLogin() {
            String SUCCESS_LOGIN_XPATH = "//li[a[contains(text(), 'Logged in as')]]/a";
            WebElement success_login = driver.findElement(By.xpath(SUCCESS_LOGIN_XPATH));
            String text = success_login.getText();
            if (text.startsWith("Logged in as")) {
                log.info("Verify correct login successful as {}.", text.substring(12).strip());
                Document foundUser = repository.getUserByEmail(email);
                UserInfo updatedInfo = mapDocumentToUser(foundUser);
                userService.login(updatedInfo.getEmail(), updatedInfo.toString());
            }
            return new Header();
        }

        public String incorrectLogin(){
            String FAILED_LOGIN_XPATH = "//div[contains(@class,'login-form')]//p[contains(.,'incorrect')]";
            WebElement errorMessage = driver.findElement(By.xpath(FAILED_LOGIN_XPATH));
            log.info("verify incorrect Login");
            return errorMessage.getText();
        }

    }
