package register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

    private String name, email, password;
    private String day, month, year;
    private boolean newsletters, special_offers;
    private String firstname, lastname, company, phone;
    private String address1, address2, country, city, state, zipcode;


}
