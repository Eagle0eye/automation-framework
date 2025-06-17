    package pages.shared.validations;


    import Cache.DTO.UserInfo;
    import mongo.AuthRepository;
    import org.bson.Document;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import pages.shared.components.BasePage;
    import Cache.Cache;
    import pages.shared.components.Header;


    public class ValidLoginPage extends BasePage {
        private final AuthRepository repository;

        public ValidLoginPage(WebDriver driver) {
            super(driver);
            repository = new AuthRepository();
        }

        public Header correctLogin() {
            String SUCCESS_LOGIN_XPATH = "//li[a[contains(text(), 'Logged in as')]]/a";
            WebElement success_login = driver.findElement(By.xpath(SUCCESS_LOGIN_XPATH));
            String text = success_login.getText();
            if (text.startsWith("Logged in as")) {
                log.info("Verify correct login successful as {}.", text.substring(12).strip());
                UserInfo userInfo = (UserInfo) Cache.get("test-account");
                Document foundUser = repository.getUserByEmail(userInfo.getEmail());
                UserInfo updatedInfo = mapDocumentToUser(userInfo,foundUser);
                Cache.put("test-account", updatedInfo);
            }
            return new Header();
        }

        public String incorrectLogin(){
            String FAILED_LOGIN_XPATH = "//div[contains(@class,'login-form')]//p[contains(.,'incorrect')]";
            WebElement errorMessage = driver.findElement(By.xpath(FAILED_LOGIN_XPATH));
            log.info("verify incorrect Login");
            return errorMessage.getText();
        }



        private UserInfo mapDocumentToUser(UserInfo userInfo,Document document) {

            return userInfo.toBuilder()
                    .name(document.getString("name"))
                    .firstname(document.getString("first_name"))
                    .lastname(document.getString("last_name"))
                    .address(document.getString("address1"))
                    .address2(document.getString("address2"))
                    .title(document.getString("title"))
                    .company(document.getString("company"))
                    .city(document.getString("city"))
                    .state(document.getString("state"))
                    .country(document.getString("country"))
                    .zipcode(document.getString("zipcode"))
                    .phone(document.getString("phone"))
                    .build();
        }
    }
