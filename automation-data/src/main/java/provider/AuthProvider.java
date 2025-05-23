package Provider;

import DTO.Login;
import org.testng.annotations.DataProvider;

import static variables.AuthVariables.*;

public class AuthorProvider {
    @DataProvider( name = "loginValidCredentials")
    public static Login[] loginValidData() {
        Login login = new Login();
        login.setEmail(validEmail);
        login.setPassword(validPassword);
        return new Login[]{login};
    }

    @DataProvider(name = "loginInvalidIncorrectPassword")
    public static Login[] loginInvalidIncorrectPassword() {
        Login login = new Login();
        login.setEmail(validEmail);
        login.setPassword(invalidPassword);
        return new Login[]{login};
    }

    @DataProvider(name = "loginInvalidEmptyEmail")
    public static Login[] loginInvalidEmptyEmail() {
        Login login = new Login();
        login.setEmail("");
        login.setPassword(validPassword);
        return new Login[]{login};
    }

    @DataProvider(name = "loginInvalidNotFoundEmail")
    public static Login[] loginInvalidNotFoundEmail() {
        Login login = new Login();
        login.setEmail(invalidEmail);
        login.setPassword(validPassword);
        return new Login[]{login};
    }

}
