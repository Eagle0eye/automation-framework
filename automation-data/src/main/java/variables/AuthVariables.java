package variables;

import DTO.Login;
import com.github.javafaker.Faker;

import static util.EmailGenerator.generate;

public class AuthVariables {
    private static final Faker faker = new Faker();

    private static Login login = generate();
    public static String validEmail = login.getEmail();
    public static String validPassword = login.getPassword();

    public static String invalidEmail = faker.internet().emailAddress();
    public static String invalidPassword =faker.internet().password();

    public static String accessToken = "";
    public static String refreshToken = "";

}
