package Cache.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private String title, name, email, password;

    private String firstname, lastname;

    private String company,address, address2;

    private String country, state, city, zipcode, phone;

    @Override
    public String toString() {
        return String.format(
                "%s. %s %s\n%s\n%s\n%s\n%s\n%s\n%s %s %s\n%s\n%s",
                title,
                firstname,
                lastname,
                email,
                password,
                company,
                address,
                address2,
                city,
                state,
                zipcode,
                country,
                phone
        );
    }
}
