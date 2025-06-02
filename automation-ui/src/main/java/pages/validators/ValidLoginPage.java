    package pages.validators;


    import Cache.DTO.UserInfo;
    import mongo.AuthRepository;
    import org.bson.Document;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import pages.BasePage;
    import Cache.Cache;


    public class ValidLoginPage extends BasePage {
        private  AuthRepository repository;
        @FindBy(css = ".navbar-nav > li:nth-child(10) > a:nth-child(1)")  private WebElement correctLogin;

        @FindBy(css = ".login-form > form:nth-child(2) > p:nth-child(4)") private WebElement errorMessage;

        public ValidLoginPage(WebDriver driver) {
            super(driver);
            repository = new AuthRepository();
        }

        public boolean correctLogin() {
            String text = correctLogin.getText();
            if (text.startsWith("Logged in as")) {
                log.info("Verify correct login successful as {}.", correctLogin.getText().substring(12, text.length()));

                UserInfo userInfo = (UserInfo) Cache.get("test-account");

                Document foundUser = repository.getUserByEmail(userInfo.getEmail());
                UserInfo updatedInfo = mapDocumentToUser(userInfo,foundUser);
                Cache.put("test-account", updatedInfo);

                return true;
            }
            return false;
        }

        public boolean incorrectLogin(){
            log.info("verify incorrect login");
            return errorMessage.getText().contentEquals("Your email or password is incorrect!");
        }



        private UserInfo mapDocumentToUser(UserInfo userInfo,Document document) {


            userInfo.setFirstname(document.getString("firstname"));
            userInfo.setLastname(document.getString("lastname"));
            userInfo.setPassword(document.getString("password"));
            userInfo.setAddress(document.getString("address"));
            userInfo.setAddress2(document.getString("address2"));
            userInfo.setCity(document.getString("city"));
            userInfo.setState(document.getString("state"));
            userInfo.setCountry(document.getString("country"));
            userInfo.setZipcode(document.getString("zipcode"));
            userInfo.setTitle(document.getString("title"));
            return userInfo;
        }
    }
