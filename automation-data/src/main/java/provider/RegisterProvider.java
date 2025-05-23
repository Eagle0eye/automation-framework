package provider;

import DTO.Register;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import static variables.AuthVariables.validEmail;
import static variables.AuthVariables.validPassword;
import static variables.UserVariables.*;

public class RegisterProvider {

    @DataProvider(name = "RegisterValidFullInfo")
    public Register[] registerValidFullInfo() {
        return new Register[]{buildFullInfo()};
    }

    @DataProvider(name = "RegisterValidMinInfo")
    public Register[] registerValidMinInfo() {
        return new Register[]{buildMandatoryInfo()};
    }

    @DataProvider(name = "RegisterInvalidInNullPassword")
    public Register[] registerValidInCompletedPassword() {
        return new Register[]{buildMandatoryInfo().toBuilder().password(null).build()};
    }
    @DataProvider(name = "RegisterInvalidCompletedBlankPassword")
    public Register[] registerInvalidCompletedBlankPassword() {
        return new Register[]{buildMandatoryInfo().toBuilder().password(" ").build()};
    }
    // Here

    @DataProvider(name = "RegisterInvalidCompletedEmptyPassword")
    public Register[] registerInvalidRegistersEmptyPassword() {
        return new Register[]{buildMandatoryInfo().toBuilder().password("").build()};
    }

    @DataProvider(name = "RegisterInvalidExistEmail")
    public Register[] registerInvalidExistEmail() {
        return new Register[]{buildFullInfo().toBuilder().email(validEmail).password(validPassword).build()};
    }


    private Register buildMandatoryInfo() {
        Faker faker = new Faker();
        return Register.builder()
                .title(title)
                .name(register_name)
                .email(faker.internet().emailAddress())
                .password(register_password)
                .firstname(firstName)
                .lastname(lastName)
                .address(address)
                .country(country)
                .state(state)
                .city(city)
                .zipcode(zip)
                .phone(phone)
                .build();
    }

    private Register buildFullInfo() {
        Faker faker = new Faker();
        return buildMandatoryInfo().toBuilder()
                .email(faker.internet().emailAddress())
                .day(day)
                .month(month)
                .year(year)
                .company(company)
                .address2(address2)
                .build();
    }
}
