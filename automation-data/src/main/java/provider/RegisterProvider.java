package Provider;


import DTO.Register;
import org.testng.annotations.DataProvider;

import static variables.AuthVariables.validEmail;
import static variables.UserVariables.*;

public class RegisterProvider {
    @DataProvider(name = "RegisterValidFullInfo")
    public Register[] registerValidFullInfo() {
        Register register = new Register();
        register.setTitle(title);
        register.setName(register_name);
        register.setEmail(register_email);
        register.setPassword(register_password);
        register.setDay(day);
        register.setMonth(month);
        register.setYear(year);
        register.setFirstname(firstName);
        register.setLastname(lastName);
        register.setCompany(company);
        register.setAddress(address);
        register.setAddress2(address2);
        register.setCountry(country);
        register.setState(state);
        register.setCity(city);
        register.setZipcode(zip);
        register.setPhone(phone);
    return new Register[]{register};
    }

    @DataProvider(name = "RegisterValidMinInfo")
    public Register[] registerValidMinInfo() {
        Register register = new Register();
        register.setName(register_name);
        register.setEmail(register_email);
        register.setPassword(register_password);
        register.setFirstname(firstName);
        register.setLastname(lastName);
        register.setAddress(address);
        register.setCountry(country);
        register.setState(state);
        register.setCity(city);
        register.setZipcode(zip);
        register.setPhone(phone);
        return new Register[]{register};
    }
    @DataProvider(name = "RegisterValidInCompletedPassword")
    public Register[] registerValidInCompletedPassword() {
        Register register = new Register();
        register.setName(register_name);
        register.setEmail(register_email);
        register.setFirstname(firstName);
        register.setLastname(lastName);
        register.setAddress(address);
        register.setCountry(country);
        register.setState(state);
        register.setCity(city);
        register.setZipcode(zip);
        register.setPhone(phone);
        return new Register[]{register};
    }

    @DataProvider(name = "RegisterInvalidCompletedBlankPassword")
    public Register[] registerInvalidCompletedBlankPassword() {
        Register register = new Register();
        register.setName(register_name);
        register.setEmail(register_email);
        register.setPassword(" ");
        register.setFirstname(firstName);
        register.setLastname(lastName);
        register.setAddress(address);
        register.setCountry(country);
        register.setState(state);
        register.setCity(city);
        register.setZipcode(zip);
        register.setPhone(phone);
        return new Register[]{register};
    }

    @DataProvider(name = "RegisterInvalidCompletedEmptyPassword")
    public Register[] registerInvalidRegistersEmptyPassword() {
        Register register = new Register();
        register.setName(register_name);
        register.setEmail(register_email);
        register.setPassword("");
        register.setFirstname(firstName);
        register.setLastname(lastName);
        register.setAddress(address);
        register.setCountry(country);
        register.setState(state);
        register.setCity(city);
        register.setZipcode(zip);
        register.setPhone(phone);
        return new Register[]{register};
    }

    @DataProvider(name = "RegisterInvalidExistEmail")
    public Register[] registerInvalidExistEmail() {
        Register register = new Register();
        register.setName(register_name);
        register.setEmail(validEmail);
        return new Register[]{register};
    }

}
